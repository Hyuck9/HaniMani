package io.github.hyuck9.hanimani.data.repository

import io.github.hyuck9.hanimani.data.entity.ToDoEntity
import io.github.hyuck9.hanimani.data.local.db.dao.ToDoDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultToDoRepository @Inject constructor(
	private val toDoDao: ToDoDao,
	private val ioDispatcher: CoroutineDispatcher
): ToDoRepository {

	override suspend fun getToDoList(): List<ToDoEntity> = withContext(ioDispatcher) {
		toDoDao.getAll()
	}

	override suspend fun getToItem(itemId: Long): ToDoEntity? = withContext(ioDispatcher) {
		toDoDao.getById(itemId)
	}

	override suspend fun insertToDoItem(toDoItem: ToDoEntity): Long = withContext(ioDispatcher) {
		toDoDao.insert(toDoItem)
	}

	override suspend fun insertToDoList(toDoList: List<ToDoEntity>) = withContext(ioDispatcher) {
		toDoDao.insert(toDoList)
	}

	override suspend fun updateToDoItem(toDoItem: ToDoEntity) = withContext(ioDispatcher) {
		toDoDao.update(toDoItem)
	}

	override suspend fun deleteAll() {
		toDoDao.deleteAll()
	}

	override suspend fun deleteToDoItem(itemId: Long) = withContext(ioDispatcher) {
		toDoDao.delete(itemId)
	}
}