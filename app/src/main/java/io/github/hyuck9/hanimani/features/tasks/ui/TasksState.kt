package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import io.github.hyuck9.hanimani.common.extension.toToDoTaskItems
import io.github.hyuck9.hanimani.model.FontSize
import io.github.hyuck9.hanimani.model.TaskAlign
import io.github.hyuck9.hanimani.model.ToDoTask

@Immutable
data class TasksState(
	val items: List<ToDoTask> = listOf(),
	val taskName: TextFieldValue = TextFieldValue(),
	val textAlign: TaskAlign = TaskAlign.START,
	val fontSize: FontSize = FontSize.SMALL
) {
	val toDoTaskItems = items.toToDoTaskItems()
	val validTaskName = taskName.text.isNotBlank()

	val maxOrder = items.maxWithOrNull(Comparator.comparingInt { lastTask ->
		lastTask.order
	})?.order ?: 0
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