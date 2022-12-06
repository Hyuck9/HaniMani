package io.github.hyuck9.hanimani.common.extension

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatAlignRight
import androidx.compose.ui.text.style.TextAlign
import io.github.hyuck9.hanimani.common.data.preference.model.TextAlignPreference
import io.github.hyuck9.hanimani.features.settings.ui.TaskAlignItem
import io.github.hyuck9.hanimani.model.TaskAlign

fun TaskAlign.toTextAlignPreference() = when (this) {
	TaskAlign.START -> TextAlignPreference.TaskAlign.START
	TaskAlign.CENTER -> TextAlignPreference.TaskAlign.CENTER
	TaskAlign.END -> TextAlignPreference.TaskAlign.END
}

fun TextAlignPreference.TaskAlign.toTaskAlign() = when (this) {
	TextAlignPreference.TaskAlign.UNRECOGNIZED,
	TextAlignPreference.TaskAlign.START -> TaskAlign.START
	TextAlignPreference.TaskAlign.CENTER -> TaskAlign.CENTER
	TextAlignPreference.TaskAlign.END -> TaskAlign.END
}


fun TaskAlign.toAlign() = when (this) {
	TaskAlign.START -> TextAlign.Start
	TaskAlign.CENTER -> TextAlign.Center
	TaskAlign.END -> TextAlign.End
}

fun TaskAlign.alignIcon() = when (this) {
	TaskAlign.START -> Icons.Default.FormatAlignLeft
	TaskAlign.CENTER -> Icons.Default.FormatAlignCenter
	TaskAlign.END -> Icons.Default.FormatAlignRight
}

fun List<TaskAlignItem>.select(taskAlign: TaskAlign): List<TaskAlignItem> {
	return map {
		it.copy(applied = it.taskAlign == taskAlign)
	}
}