package io.github.hyuck9.hanimani.data.repository

import io.github.hyuck9.hanimani.data.entity.Task
import io.github.hyuck9.hanimani.data.local.db.dao.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultToDoRepository @Inject constructor(
	private val taskDao: TaskDao,
	private val ioDispatcher: CoroutineDispatcher
): ToDoRepository {

	override suspend fun getToDoList(): List<Task> = withContext(ioDispatcher) {
		taskDao.getAll()
	}

	override suspend fun getToItem(itemId: Long): Task? = withContext(ioDispatcher) {
		taskDao.getById(itemId)
	}

	override suspend fun insertToDoItem(toDoItem: Task): Long = withContext(ioDispatcher) {
		taskDao.insert(toDoItem)
	}

	override suspend fun insertToDoList(toDoList: List<Task>) = withContext(ioDispatcher) {
		taskDao.insert(toDoList)
	}

	override suspend fun updateToDoItem(toDoItem: Task) = withContext(ioDispatcher) {
		taskDao.update(toDoItem)
	}

	override suspend fun deleteAll() {
		taskDao.deleteAll()
	}

	override suspend fun deleteToDoItem(itemId: Long) = withContext(ioDispatcher) {
		taskDao.delete(itemId)
	}
}