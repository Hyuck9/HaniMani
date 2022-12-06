package io.github.hyuck9.hanimani.features.settings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.common.theme.MediumRadius
import io.github.hyuck9.hanimani.common.uicomponent.HmIcon
import io.github.hyuck9.hanimani.common.uicomponent.HmIconButton
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
			onClickTextAlign = { viewModel.dispatch(SettingsAction.SelectTaskAlign(it)) }
		)
	}
}


@Composable
fun DesignCard(
	modifier: Modifier = Modifier,
	onClickTheme: () -> Unit,
	aligns: List<TaskAlignItem>,
	onClickTextAlign: (TaskAlignItem) -> Unit
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
			onClick = onClickTheme
		)
		SettingsRow(settingName = stringResource(id = R.string.setting_font_size), onClick = {})
		TextAlignSettingsRow(
			shape = RoundedCornerShape(bottomStart = MediumRadius, bottomEnd = MediumRadius),
			aligns = aligns,
			onClick = onClickTextAlign
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
		})
}

@Composable
private fun TextSettingsRow(
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
		TextAlignSettingsRow(aligns = mockData, onClick = {})
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
				aligns = mockData,
				onClickTextAlign = {}
			)
		}
	}
}

private val mockData = listOf(
	TaskAlignItem(TaskAlign.START, false),
	TaskAlignItem(TaskAlign.CENTER, true),
	TaskAlignItem(TaskAlign.END, false)
)