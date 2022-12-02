package io.github.hyuck9.hanimani.features.settings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.common.theme.MediumRadius

@Composable
fun SettingsScreen(
) {
	Column(
		modifier = Modifier
			.padding(16.dp)
	) {
		DesignCard()
	}
}


@Composable
fun DesignCard(
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
	) {
		Text(
			text = stringResource(id = R.string.setting_title_design),
			style = MaterialTheme.typography.titleMedium
		)
		Spacer(Modifier.height(16.dp))
		SettingsRow(
			settingName = stringResource(id = R.string.setting_theme),
			shape = RoundedCornerShape(topStart = MediumRadius, topEnd = MediumRadius),
			onClick = {}
		)
		Spacer(Modifier.height(1.dp))
		SettingsRow(settingName = stringResource(id = R.string.setting_font_size), onClick = {})
		Spacer(Modifier.height(1.dp))
		SettingsRow(
			settingName = stringResource(id = R.string.setting_font_align),
			shape = RoundedCornerShape(bottomStart = MediumRadius, bottomEnd = MediumRadius),
			onClick = {}
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsRow(
	settingName: String,
	shape: Shape = RectangleShape,
	onClick: () -> Unit
) {
	Surface(
		modifier = Modifier
			.fillMaxWidth(),
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




@Preview(showBackground = true)
@Composable
private fun SettingsRowPreview() {
	HaniManiTheme {
		SettingsRow(settingName = stringResource(id = R.string.setting_theme), onClick = {})
	}
}


@Preview(showBackground = true)
@Composable
private fun DesignCardPreview() {
	HaniManiTheme {
		SettingsScreen()
	}
}