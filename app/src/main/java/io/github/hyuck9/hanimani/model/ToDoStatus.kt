package io.github.hyuck9.hanimani.model

enum class ToDoStatus {
	IN_PROGRESS, COMPLETE;

	fun toggle(): ToDoStatus = when (this) {
		IN_PROGRESS -> COMPLETE
		COMPLETE -> IN_PROGRESS
	}
}