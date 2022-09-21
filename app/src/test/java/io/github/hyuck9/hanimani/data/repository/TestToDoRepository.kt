package io.github.hyuck9.hanimani.data.repository

import io.github.hyuck9.hanimani.data.entity.ToDoEntity

class TestToDoRepository: ToDoRepository {

	private val toDoList: MutableList<ToDoEntity> = mutableListOf()

	override suspend fun getToDoList(): List<ToDoEntity> {
		return toDoList
	}

	override suspend fun getToItem(itemId: Long): ToDoEntity? {
		return toDoList.find { it.id == itemId  }
	}

	override suspend fun insertToDoItem(toDoItem: ToDoEntity): Long {
		toDoList.add(toDoItem)
		return toDoItem.id
	}

	override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
		this.toDoList.addAll(toDoList)
	}

	override suspend fun updateToDoItem(toDoItem: ToDoEntity) {
		val foundToDoEntity = toDoList.find { it.id == toDoItem.id }
		this.toDoList[toDoList.indexOf(foundToDoEntity)] = toDoItem
	}

	override suspend fun deleteToDoItem(itemId: Long) {
		val foundToDoEntity = toDoList.find { it.id == itemId }
		this.toDoList.removeAt(toDoList.indexOf(foundToDoEntity))
	}

	override suspend fun deleteAll() {
		toDoList.clear()
	}
}