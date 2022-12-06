package io.github.hyuck9.hanimani.features.settings.ui

import androidx.compose.runtime.Immutable
import io.github.hyuck9.hanimani.model.TaskAlign

@Immutable
data class SettingsState(
	val taskAligns: List<TaskAlignItem> = listOf()
)

data class TaskAlignItem(
	val taskAlign: TaskAlign,
	val applied: Boolean
)