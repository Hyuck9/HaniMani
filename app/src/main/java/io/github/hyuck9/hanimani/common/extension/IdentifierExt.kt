package io.github.hyuck9.hanimani.common.extension

import io.github.hyuck9.hanimani.features.tasks.ui.ToDoTaskItem

fun ToDoTaskItem.identifier() = when (this) {
	is ToDoTaskItem.CompleteHeader -> id
	is ToDoTaskItem.Complete -> toDoTask.id
	is ToDoTaskItem.InProgress -> toDoTask.id
}