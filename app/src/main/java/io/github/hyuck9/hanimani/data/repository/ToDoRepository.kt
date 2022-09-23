package io.github.hyuck9.hanimani.data.repository

import io.github.hyuck9.hanimani.data.entity.Task

/**
 * 1. insertToDoList
 * 2. getToDoList
 * 3. updateToDoItem
 */
interface ToDoRepository {

	suspend fun getToDoList(): List<Task>

	suspend fun insertToDoItem(toDoItem: Task): Long

	suspend fun insertToDoList(toDoList: List<Task>)

	suspend fun updateToDoItem(toDoItem: Task)

	suspend fun getToItem(itemId: Long): Task?

	suspend fun deleteToDoItem(itemId: Long)

	suspend fun deleteAll()


}