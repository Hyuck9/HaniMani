package io.github.hyuck9.hanimani.features.tasks.ui

sealed class TasksEffect {
	data class ScrollTo(val position: Int) : TasksEffect()
}