package io.github.hyuck9.hanimani.features.theme.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.common.theme.LightPrimary
import io.github.hyuck9.hanimani.common.theme.NightItemBackgroundL2
import io.github.hyuck9.hanimani.common.theme.NightPrimary
import io.github.hyuck9.hanimani.common.uicomponent.HmIcon
import io.github.hyuck9.hanimani.common.uicomponent.HmModalBackHeader
import io.github.hyuck9.hanimani.common.uicomponent.HmModalCell
import io.github.hyuck9.hanimani.common.uicomponent.HmModalLayout
import io.github.hyuck9.hanimani.model.Theme


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ThemeScreen(
	viewModel: ThemeViewModel,
	onClickBack: () -> Unit
) {
	val state by viewModel.state.collectAsStateWithLifecycle()

	HmModalLayout(
		title = {
			HmModalBackHeader(
				text = stringResource(id = R.string.setting_theme),
				onClickBack = onClickBack
			)
		},
		content = {
			items(state.items) { item ->
				ThemeItem(
					onClick = {
						viewModel.dispatch(ThemeAction.SelectTheme(item))
					},
					item = item
				)
				Spacer(Modifier.height(8.dp))
			}
		}
	)
}

@Composable
private fun ThemeItem(
	onClick: () -> Unit,
	item: ThemeItem
) {
	HmModalCell(
		onClick = onClick,
		text = stringResource(item.title),
		color = if (item.applied) {
			MaterialTheme.colorScheme.primary
		} else {
			MaterialTheme.colorScheme.surfaceVariant
		},
		leftIcon = { LeftIcon(item = item) },
		rightIcon = if (item.applied) {
			@Composable {
				HmIcon(
					imageVector = Icons.Rounded.Check,
					modifier = Modifier.size(28.dp)
				)
			}
		} else {
			null
		}

	)
}


@Composable
private fun LeftIcon(
	item: ThemeItem
) {
	@SuppressLint("InlinedApi")
	val brush = if (item.theme == Theme.WALLPAPER) {
		val context = LocalContext.current
		Brush.linearGradient(
			colors = if (isSystemInDarkTheme()) {
				listOf(
					Color(context.resources.getColor(android.R.color.system_accent1_200, context.theme)),
					Color(context.resources.getColor(android.R.color.system_accent2_700, context.theme)),
				)
			} else {
				listOf(
					Color(context.resources.getColor(android.R.color.system_accent1_600, context.theme)),
					Color(context.resources.getColor(android.R.color.system_accent2_100, context.theme))
				)
			}
		)
	} else {
		item.brush
	}
	Box(
		modifier = Modifier
			.size(34.dp)
			.background(
				brush = brush,
				shape = CircleShape
			)
	)
}




@Preview
@Composable
private fun LeftIconPreview() {
	val themeItem = ThemeItem(
		R.string.setting_theme_light,
		Theme.LIGHT,
		Brush.linearGradient(
			colors = listOf(LightPrimary, Color.White)
		),
		false
	)
	HaniManiTheme {
		LeftIcon(item = themeItem)
	}
}