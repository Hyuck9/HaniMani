package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatAlignRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.common.theme.MediumRadius

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsRow(
	settingName: String,
	shape: Shape = RectangleShape,
	onClick: () -> Unit
) {
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 1.dp),
		shadowElevation = 5.dp,
		shape = shape,
		onClick = onClick
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp)
		) {
			Text(
				text = settingName,
				style = MaterialTheme.typography.bodyLarge
			)
		}
	}
}

@Composable
fun TextSettingsRow(
	settingName: String,
	shape: Shape = RectangleShape,
	content: LazyListScope.() -> Unit
) {
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 1.dp),
		shadowElevation = 5.dp,
		shape = shape
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text(
				modifier = Modifier
					.padding(vertical = 16.dp),
				text = settingName,
				style = MaterialTheme.typography.bodyLarge
			)

			LazyRow(content = content)

		}
	}
}

@Composable
fun SwitchSettingsRow(
	settingName: String,
	isChecked: Boolean,
	onCheckedChange: (Boolean) -> Unit,
	shape: Shape = RectangleShape
) {
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 1.dp),
		shadowElevation = 5.dp,
		shape = shape
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text(
				modifier = Modifier
					.padding(vertical = 16.dp),
				text = settingName,
				style = MaterialTheme.typography.bodyLarge
			)

			Switch(checked = isChecked, onCheckedChange = onCheckedChange)
		}
	}
}



@Preview(showBackground = true)
@Composable
private fun SettingsRowPreview() {
	HaniManiTheme {
		SettingsRow(settingName = stringResource(id = R.string.setting_theme), onClick = {})
	}
}

@Preview(showBackground = true)
@Composable
private fun TextSettingsRowPreview() {
	HaniManiTheme {
		TextSettingsRow(
			settingName = stringResource(id = R.string.setting_font_size),
			content = {
				items(3) {
					HmIconButton(
						onClick = {  },
						color = Color.Transparent
					) {
						HmIcon(
							imageVector = Icons.Default.FormatAlignLeft,
							tint = MaterialTheme.colorScheme.primary
						)
						HmIcon(
							imageVector = Icons.Default.FormatAlignCenter,
							tint = MaterialTheme.colorScheme.surfaceVariant
						)
						HmIcon(
							imageVector = Icons.Default.FormatAlignRight,
							tint = MaterialTheme.colorScheme.surfaceVariant
						)
					}
				}
			}
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun SwitchSettingsRowPreview() {
	HaniManiTheme {
		SwitchSettingsRow(
			settingName = stringResource(id = R.string.setting_function_autorun),
			shape = RoundedCornerShape(MediumRadius),
			isChecked = true,
			onCheckedChange = {  }
		)
	}
}