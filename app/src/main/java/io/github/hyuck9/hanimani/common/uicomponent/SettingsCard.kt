package io.github.hyuck9.hanimani.common.uicomponent

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.common.theme.MediumRadius

@Composable
fun SettingsCard(
	modifier: Modifier = Modifier,
	@StringRes titleResId: Int,
	content: @Composable ColumnScope.() -> Unit,
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
	) {
		Text(
			text = stringResource(id = titleResId),
			style = MaterialTheme.typography.titleMedium
		)
		Spacer(Modifier.height(16.dp))
		content()
	}
}



@Preview(showBackground = true)
@Composable
private fun SettingsCardPreview() {
	HaniManiTheme {
		Column(
			modifier = Modifier
				.padding(16.dp)
		) {
			SettingsCard(
				titleResId = R.string.setting_title_function,
				content = {
					SwitchSettingsRow(
						settingName = stringResource(id = R.string.setting_function_autorun),
						shape = RoundedCornerShape(MediumRadius),
						isChecked = true,
						onCheckedChange = { }
					)
				})
		}
	}
}