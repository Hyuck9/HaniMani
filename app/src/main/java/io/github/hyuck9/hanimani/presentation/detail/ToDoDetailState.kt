package io.github.hyuck9.hanimani.presentation.detail

import io.github.hyuck9.hanimani.data.entity.ToDoEntity

sealed class ToDoDetailState {

	object UnInitialized: ToDoDetailState()

	object Loading: ToDoDetailState()

	data class Success(
		val toDoItem: ToDoEntity
	): ToDoDetailState()

	object Delete: ToDoDetailState()

	object Modify: ToDoDetailState()

	object Error: ToDoDetailState()

	object Write: ToDoDetailState()

}
