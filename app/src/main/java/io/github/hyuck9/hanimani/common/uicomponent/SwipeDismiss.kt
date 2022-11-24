package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.extension.drawGrowingCircle
import io.github.hyuck9.hanimani.common.extension.lerp
import io.github.hyuck9.hanimani.common.extension.onPositionInParentChanged
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.hypot

private const val dismissFraction = 0.4f
private const val iconShownFraction = 0.07f

object ContentVisibility {
	const val visible: Float = 1f
	const val hidden: Float = 0f
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeDismiss(
	modifier: Modifier = Modifier,
	backgroundModifier: Modifier = Modifier,
	backgroundSecondaryModifier: Modifier = Modifier,
	content: @Composable (isDismissed: Boolean) -> Unit,
	onDismiss: () -> Unit,
	onComplete: () -> Unit
) {
	var isStartToEnd by remember { mutableStateOf(false) }
	val dismissState = rememberDismissState(
		confirmStateChange = { dismissValue ->
			when (dismissValue) {
				DismissValue.Default -> {
					isStartToEnd = false
					false
				}
				DismissValue.DismissedToStart -> {
					isStartToEnd = false
					true
				}
				DismissValue.DismissedToEnd -> {
					isStartToEnd = true
					true
				}
			}
		}
	)
	SwipeDismiss(
		modifier = modifier,
		background = { _, fraction ->
			Background(fraction, backgroundModifier, backgroundSecondaryModifier, isStartToEnd)
		},
		content = content,
		dismissState = dismissState,
		onDismiss = onDismiss,
		onComplete = onComplete
	)
}

@Composable
private fun Background(
	fraction: Float,
	modifier: Modifier = Modifier,
	backgroundSecondaryModifier: Modifier,
	isStartToEnd: Boolean,
) {

	val wouldCompleteOnRelease = fraction.absoluteValue >= dismissFraction
	val iconVisible = fraction.absoluteValue >= iconShownFraction

	var bounceState by remember { mutableStateOf(false) }
	val lottieIcon by rememberLottieComposition(
		if (isStartToEnd) {
			LottieCompositionSpec.RawRes(R.raw.lottie_complete)
		} else {
			LottieCompositionSpec.RawRes(R.raw.lottie_delete)
		}
	)
	val lottieAnimatable = rememberLottieAnimatable()
	var iconCenter by remember { mutableStateOf(Offset(0f, 0f)) }

	val circleFraction by animateFloatAsState(
		targetValue = if (wouldCompleteOnRelease) ContentVisibility.visible else ContentVisibility.hidden,
		animationSpec = tween(durationMillis = 600)
	)
	val bounceInOut by animateFloatAsState(
		targetValue = if (bounceState) 4f else 3f
	)

	val maxRadius = if (isStartToEnd) 1000.0 else hypot(iconCenter.x.toDouble(), iconCenter.y.toDouble())

	LaunchedEffect(wouldCompleteOnRelease) {
		if (wouldCompleteOnRelease) {
			launch {
				bounceState = true
				delay(100)
				bounceState = false
			}
		}
	}
	LaunchedEffect(iconVisible) {
		if (iconVisible) {
			launch {
				delay(50)
				lottieAnimatable.animate(
					composition = lottieIcon,
				)
			}
		}
	}

	Box(
		modifier = modifier
			.fillMaxSize()
	) {
		Spacer(
			modifier = backgroundSecondaryModifier
				.fillMaxSize()
				.drawGrowingCircle(
					color = if (isStartToEnd) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
					center = iconCenter,
					radius = lerp(
						startValue = 0f,
						endValue = maxRadius.toFloat(),
						fraction = FastOutLinearInEasing.transform(circleFraction)
					)
				)
		)

		Box(
			Modifier
				.align(if (isStartToEnd) Alignment.CenterStart else Alignment.CenterEnd)
				.padding(horizontal = 16.dp)
				.onPositionInParentChanged { iconCenter = it.boundsInParent().center }
		) {
			LottieAnimation(
				composition = lottieIcon,
				progress = { lottieAnimatable.progress },
				modifier = Modifier
					.size(32.dp)
					.scale(bounceInOut)
			)
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeDismiss(
	modifier: Modifier = Modifier,
	background: @Composable (isDismissed: Boolean, fraction: Float) -> Unit,
	content: @Composable (isDismissed: Boolean) -> Unit,
	directions: Set<DismissDirection> = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
	enter: EnterTransition = expandVertically(),
	exit: ExitTransition = shrinkVertically(
		animationSpec = tween(
			durationMillis = 400,
		)
	),
	dismissState: DismissState,
	onDismiss: () -> Unit,
	onComplete: () -> Unit
) {
	val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

	LaunchedEffect(dismissState.currentValue) {
		if (dismissState.currentValue == DismissValue.DismissedToStart) {
			delay(600)
			onDismiss()
		} else if (dismissState.currentValue == DismissValue.DismissedToEnd) {
			onComplete()
		}
	}

	AnimatedVisibility(
		modifier = modifier,
		visible = !isDismissed,
		enter = enter,
		exit = exit
	) {
		SwipeToDismiss(
			modifier = modifier,
			state = dismissState,
			directions = directions,
			background = {
				if (dismissState.dismissDirection != null && dismissState.dismissDirection in directions) {
					val fraction = dismissState.progress.fraction
					background(isDismissed, fraction)
				}
			},
			dismissContent = { content(isDismissed) },
			dismissThresholds = { FractionalThreshold(dismissFraction) }
		)
	}
}