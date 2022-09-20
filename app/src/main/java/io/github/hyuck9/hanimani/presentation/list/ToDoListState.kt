package io.github.hyuck9.hanimani.presentation.list

import io.github.hyuck9.hanimani.data.entity.ToDoEntity

sealed class ToDoListState {

	object UnInitialized: ToDoListState()

	object Loading: ToDoListState()

	data class Success(
		val toDoList: List<ToDoEntity>
	): ToDoListState()

	object Error: ToDoListState()

}
