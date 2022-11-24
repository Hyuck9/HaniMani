package io.github.hyuck9.hanimani.common.extension

import io.github.hyuck9.hanimani.features.tasks.ui.ToDoTaskItem
import io.github.hyuck9.hanimani.model.ToDoStatus
import io.github.hyuck9.hanimani.model.ToDoTask

fun List<ToDoTask>.toToDoTaskItems(): List<ToDoTaskItem> {
	val tasks = this.map {
		when (it.status) {
			ToDoStatus.COMPLETE -> ToDoTaskItem.Complete(it)
			ToDoStatus.IN_PROGRESS -> ToDoTaskItem.InProgress(it)
		}
	}
		.sortedBy { it is ToDoTaskItem.Complete }
		.toMutableList()

	val firstCompleteIndex = tasks.indexOfFirst { it is ToDoTaskItem.Complete }

	if (firstCompleteIndex != -1) {
		tasks.add(firstCompleteIndex, ToDoTaskItem.CompleteHeader())
	}

	return tasks
}