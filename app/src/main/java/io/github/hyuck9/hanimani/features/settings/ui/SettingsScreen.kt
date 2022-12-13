package io.github.hyuck9.hanimani.features.settings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.extension.alignIcon
import io.github.hyuck9.hanimani.common.extension.toTextStyle
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.common.theme.MediumRadius
import io.github.hyuck9.hanimani.common.uicomponent.*
import io.github.hyuck9.hanimani.model.FontSize
import io.github.hyuck9.hanimani.model.TaskAlign

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SettingsScreen(
	viewModel: SettingsViewModel,
	onClickTheme: () -> Unit,
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	Column(
		modifier = Modifier
			.padding(16.dp)
	) {
		DesignCard(
			onClickTheme = onClickTheme,
			aligns = state.taskAligns,
			sizes = state.fontSizes,
			onAlignClick = { viewModel.dispatch(SettingsAction.SelectTaskAlign(it)) },
			onSizeClick = { viewModel.dispatch(SettingsAction.SelectFontSize(it)) }
		)

		FunctionCard(
			modifier = Modifier.padding(top = 16.dp),
			isAutorunCheck = state.isAutorun,
			onAutorunCheckedChange = { viewModel.dispatch(SettingsAction.OnAutorunCheckedChange(it)) }
		)
	}
}


@Composable
fun DesignCard(
	modifier: Modifier = Modifier,
	onClickTheme: () -> Unit,
	aligns: List<TaskAlignItem>,
	sizes: List<FontSizeItem>,
	onAlignClick: (TaskAlignItem) -> Unit,
	onSizeClick: (FontSizeItem) -> Unit
) {

	SettingsCard(
		modifier = modifier,
		titleResId = R.string.setting_title_design,
		content = {
			SettingsRow(
				settingName = stringResource(id = R.string.setting_theme),
				shape = RoundedCornerShape(topStart = MediumRadius, topEnd = MediumRadius),
				onClick = onClickTheme
			)
			FontSizeSettingsRow(
				sizes = sizes,
				onClick = onSizeClick
			)
			TextAlignSettingsRow(
				shape = RoundedCornerShape(bottomStart = MediumRadius, bottomEnd = MediumRadius),
				aligns = aligns,
				onClick = onAlignClick
			)
		})
}

@Composable
fun FunctionCard(
	modifier: Modifier = Modifier,
	isAutorunCheck: Boolean,
	onAutorunCheckedChange: (Boolean) -> Unit,
) {

	SettingsCard(
		modifier = modifier,
		titleResId = R.string.setting_title_function,
		content = {
			SwitchSettingsRow(
				settingName = stringResource(id = R.string.setting_function_autorun),
				shape = RoundedCornerShape(MediumRadius),
				isChecked = isAutorunCheck,
				onCheckedChange = onAutorunCheckedChange
			)
		})
}

@Composable
private fun TextAlignSettingsRow(
	shape: Shape = RectangleShape,
	aligns: List<TaskAlignItem>,
	onClick: (TaskAlignItem) -> Unit
) {
	TextSettingsRow(
		shape = shape,
		settingName = stringResource(id = R.string.setting_font_align),
		content = {
			items(aligns) {
				HmIconButton(
					onClick = { onClick(it) },
					color = Color.Transparent
				) {
					HmIcon(
						imageVector = it.taskAlign.alignIcon(),
						tint = if (it.applied) {
							MaterialTheme.colorScheme.primary
						} else {
							MaterialTheme.colorScheme.surfaceVariant
						}
					)
				}
			}
		}
	)
}

@Composable
private fun FontSizeSettingsRow(
	shape: Shape = RectangleShape,
	sizes: List<FontSizeItem>,
	onClick: (FontSizeItem) -> Unit
) {
	TextSettingsRow(
		shape = shape,
		settingName = stringResource(id = R.string.setting_font_size),
		content = {
			items(sizes) {
				HmIconButton(
					onClick = { onClick(it) },
					color = Color.Transparent
				) {
					Text(
						text = "a",
						style = it.fontSize.toTextStyle(),
						color = if (it.applied) {
							MaterialTheme.colorScheme.primary
						} else {
							MaterialTheme.colorScheme.surfaceVariant
						}
					)
				}
			}
		}
	)
}



@Preview(showBackground = true)
@Composable
private fun TextAlignSettingsRowPreview() {
	HaniManiTheme {
		TextAlignSettingsRow(aligns = mockTaskAligns(), onClick = {})
	}
}

@Preview(showBackground = true)
@Composable
private fun FontSizeSettingsRowPreview() {
	HaniManiTheme {
		FontSizeSettingsRow(sizes = mockFontSizes(), onClick = {})
	}
}



@Preview(showBackground = true)
@Composable
private fun DesignCardPreview() {
	HaniManiTheme {
		Column(
			modifier = Modifier
				.padding(16.dp)
		) {
			DesignCard(
				onClickTheme = {},
				aligns = mockTaskAligns(),
				sizes = mockFontSizes(),
				onAlignClick = {},
				onSizeClick = {}
			)
		}
	}
}


@Preview(showBackground = true)
@Composable
private fun FunctionCardPreview() {
	HaniManiTheme {
		Column(
			modifier = Modifier
				.padding(16.dp)
		) {
			FunctionCard(
				isAutorunCheck = true,
				onAutorunCheckedChange = {},
			)
		}
	}
}

private fun mockTaskAligns() = listOf(
	TaskAlignItem(TaskAlign.START, false),
	TaskAlignItem(TaskAlign.CENTER, true),
	TaskAlignItem(TaskAlign.END, false)
)

private fun mockFontSizes() = listOf(
	FontSizeItem(FontSize.SMALL, true),
	FontSizeItem(FontSize.MEDIUM, false),
	FontSizeItem(FontSize.LARGE, false),
)