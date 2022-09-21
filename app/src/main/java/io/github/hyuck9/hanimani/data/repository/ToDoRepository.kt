package io.github.hyuck9.hanimani.data.repository

import io.github.hyuck9.hanimani.data.entity.ToDoEntity

/**
 * 1. insertToDoList
 * 2. getToDoList
 * 3. updateToDoItem
 */
interface ToDoRepository {

	suspend fun getToDoList(): List<ToDoEntity>

	suspend fun insertToDoItem(toDoItem: ToDoEntity): Long

	suspend fun insertToDoList(toDoList: List<ToDoEntity>)

	suspend fun updateToDoItem(toDoItem: ToDoEntity)

	suspend fun getToItem(itemId: Long): ToDoEntity?

	suspend fun deleteToDoItem(itemId: Long)

	suspend fun deleteAll()


}