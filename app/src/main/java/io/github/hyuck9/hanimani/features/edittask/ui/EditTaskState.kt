package io.github.hyuck9.hanimani.features.edittask.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import io.github.hyuck9.hanimani.model.ToDoTask

@Immutable
data class EditTaskState(
	val task: ToDoTask = ToDoTask(),
	val taskName: TextFieldValue = TextFieldValue()
) {
	val validTaskName = taskName.text.isNotBlank()
}