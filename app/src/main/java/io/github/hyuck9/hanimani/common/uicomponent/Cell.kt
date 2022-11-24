package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.common.theme.DividerAlpha
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme

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
				Column {
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
		},
		onDismiss = { onSwipeToDelete() }
	)
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