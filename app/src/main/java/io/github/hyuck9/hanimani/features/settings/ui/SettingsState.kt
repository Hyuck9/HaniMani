package io.github.hyuck9.hanimani.features.settings.ui

import androidx.compose.runtime.Immutable
import io.github.hyuck9.hanimani.model.FontSize
import io.github.hyuck9.hanimani.model.TaskAlign

@Immutable
data class SettingsState(
	val taskAligns: List<TaskAlignItem> = listOf(),
	val fontSizes: List<FontSizeItem> = listOf(),
	val isAutorun: Boolean = true,
	val isHideCompleteTasks: Boolean = false,
)

data class TaskAlignItem(
	val taskAlign: TaskAlign,
	val applied: Boolean
)

data class FontSizeItem(
	val fontSize: FontSize,
	val applied: Boolean
)