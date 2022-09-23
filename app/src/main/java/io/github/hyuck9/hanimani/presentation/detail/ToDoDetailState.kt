package io.github.hyuck9.hanimani.presentation.detail

import io.github.hyuck9.hanimani.data.entity.Task

sealed class ToDoDetailState {

	object UnInitialized: ToDoDetailState()

	object Loading: ToDoDetailState()

	data class Success(
		val toDoItem: Task
	): ToDoDetailState()

	object Delete: ToDoDetailState()

	object Modify: ToDoDetailState()

	object Error: ToDoDetailState()

	object Write: ToDoDetailState()

}
