package io.github.hyuck9.hanimani.features.tasks.ui

import io.github.hyuck9.hanimani.model.ToDoTask

sealed class TasksEffect {
	data class ScrollTo(val position: Int) : TasksEffect()
	data class OnUndoDeleteSnackBar(val task: ToDoTask) : TasksEffect()
}