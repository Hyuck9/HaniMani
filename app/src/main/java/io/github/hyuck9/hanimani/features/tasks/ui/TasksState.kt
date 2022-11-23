package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.compose.ui.text.input.TextFieldValue
import io.github.hyuck9.hanimani.model.ToDoTask

data class TasksState(
	val items: List<ToDoTaskItem> = emptyList(),
	val taskName: TextFieldValue = TextFieldValue()
) {
	val validTaskName = taskName.text.isNotBlank()
}

sealed class ToDoTaskItem {
	data class CompleteHeader(val id: String = "CompleteHeader") : ToDoTaskItem()

	data class Complete(
		val toDoTask: ToDoTask
	) : ToDoTaskItem()

	data class InProgress(
		val toDoTask: ToDoTask
	) : ToDoTaskItem()
}