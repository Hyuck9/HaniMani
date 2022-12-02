package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.common.theme.AlphaDisabled
import io.github.hyuck9.hanimani.common.theme.AlphaHigh
import io.github.hyuck9.hanimani.common.theme.DividerAlpha
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme

@Composable
fun HmModalCell(
	onClick: () -> Unit,
	text: String,
	color: Color = MaterialTheme.colorScheme.surfaceVariant,
	textColor: Color = Color.Unspecified,
	enabled: Boolean = true,
	leftIcon: @Composable (()-> Unit)? = null,
	rightIcon: @Composable (()-> Unit)? = null
) {
	val colorAlpha = if (enabled) {
		AlphaHigh
	} else {
		AlphaDisabled
	}
	val onClickState = if (enabled) {
		onClick
	} else {
		{}
	}
	val indication = if (enabled) {
		LocalIndication.current
	} else {
		null
	}

	val shape = MaterialTheme.shapes.medium
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
			.height(56.dp)
			.clip(shape)
			.clickable(
				onClick = onClickState,
				indication = indication,
				interactionSource = remember { MutableInteractionSource() }
			),
		shape = shape,
		color = color.copy(alpha = colorAlpha)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			if (leftIcon != null) {
				Spacer(modifier = Modifier.width(8.dp))
				leftIcon()
				Spacer(modifier = Modifier.width(16.dp))
			} else {
				Spacer(modifier = Modifier.width(20.dp))
			}

			Text(
				text = text,
				style = MaterialTheme.typography.titleSmall,
				color = textColor
			)

			if (rightIcon != null) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.End
				) {
					rightIcon()
					Spacer(modifier = Modifier.size(20.dp))
				}
			}
		}
	}
}


@Composable
fun HmToDoItemCell(
	modifier: Modifier = Modifier,
	name: String,
	checkboxColor: Color,
	contentPaddingValues: PaddingValues,
	leftIcon: ImageVector,
	textDecoration: TextDecoration?,
	onClick: () -> Unit,
	onSwipeToDelete: () -> Unit,
	onCheckboxClick: () -> Unit
) {
	SwipeDismiss(
		modifier = modifier,
		backgroundModifier = Modifier.background(MaterialTheme.colorScheme.secondary),
		content = {
			Surface(
				modifier = Modifier
					.fillMaxWidth()
					.clickable(onClick = onClick)
			) {
				ContentRow(
					contentPaddingValues = contentPaddingValues,
					onCheckboxClick = onCheckboxClick,
					leftIcon = leftIcon,
					checkboxColor = checkboxColor,
					name = name,
					textDecoration = textDecoration
				)
			}
		},
		onDismiss = { onSwipeToDelete() },
		onComplete = onCheckboxClick
	)
}

@Composable
private fun ContentRow(
	modifier: Modifier = Modifier,
	contentPaddingValues: PaddingValues,
	onCheckboxClick: () -> Unit,
	leftIcon: ImageVector,
	checkboxColor: Color,
	name: String,
	textDecoration: TextDecoration?
) {
	Column(
		modifier = modifier
			.background(MaterialTheme.colorScheme.surface)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.padding(contentPaddingValues)
		) {
			HmIconButton(
				onClick = onCheckboxClick,
				color = Color.Transparent
			) {
				HmIcon(
					imageVector = leftIcon,
					tint = checkboxColor
				)
			}

			Text(
				text = name,
				style = MaterialTheme.typography.titleSmall.copy(textDecoration = textDecoration),
			)
		}
		Divider(
			color = MaterialTheme.colorScheme.onSurface.copy(alpha = DividerAlpha)
		)
	}
}



@Preview
@Composable
private fun HmModalCellPreview() {
	HaniManiTheme {
		Surface {
			HmModalCell(
				onClick = {},
				text = "테스트"
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun HmToDoItemCellPreview() {
	HaniManiTheme {
		HmToDoItemCell(
			modifier = Modifier.padding(bottom = 8.dp),
			name = "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세\n무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세.",
			checkboxColor = Color.LightGray,
			contentPaddingValues = PaddingValues(all = 8.dp),
			leftIcon = Icons.Rounded.CheckCircle,
			textDecoration = TextDecoration.None,
			onClick = {},
			onSwipeToDelete = {},
			onCheckboxClick = {}
		)
	}
}