package io.github.hyuck9.hanimani.common.data.repository

import io.github.hyuck9.hanimani.common.data.local.TasksDao
import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.common.extension.toTaskEntity
import io.github.hyuck9.hanimani.common.extension.toToDoTask
import io.github.hyuck9.hanimani.common.extension.toToDoTasks
import io.github.hyuck9.hanimani.common.extension.toTrashEntity
import io.github.hyuck9.hanimani.model.ToDoStatus
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class DefaultTasksRepository(
	private val tasksDao: TasksDao,
	private val ioDispatcher: CoroutineDispatcher
) : TasksRepository {

	override fun getTasks(): Flow<List<ToDoTask>> {
		return tasksDao.observeTasks().map {
			it.toToDoTasks()
		}
	}

	override fun getTaskById(taskId: String): Flow<ToDoTask> {
		return tasksDao.observeTaskById(taskId).map {
			it.toToDoTask()
		}
	}

	override suspend fun saveTask(task: ToDoTask) = withContext(ioDispatcher) {
		tasksDao.saveTask(taskEntity = task.toTaskEntity())
	}

	override suspend fun updateTaskStatus(taskId: String, status: ToDoStatus, completedAt: LocalDateTime?, updatedAt: LocalDateTime) = withContext(ioDispatcher) {
		tasksDao.updateTaskStatus(taskId, status, completedAt, updatedAt)
	}

	override suspend fun updateOrder(taskId: String, order: Int, updatedAt: LocalDateTime) = withContext(ioDispatcher) {
		tasksDao.updateOrder(taskId, order, updatedAt)
	}

	override suspend fun updateTasks(tasks: List<TaskEntity>) = withContext(ioDispatcher) {
		tasksDao.updateTasks(tasks)
	}

	override suspend fun updateTaskName(taskId: String, name: String, updatedAt: LocalDateTime) {
		tasksDao.updateTaskName(taskId, name, updatedAt)
	}

	override suspend fun unDoTask(task: ToDoTask) = withContext(ioDispatcher) {
		tasksDao.deleteTrashById(task.id)
		tasksDao.saveTask(task.toTrashEntity().toTaskEntity())
	}

	override suspend fun deleteTaskById(task: ToDoTask) = withContext(ioDispatcher) {
		tasksDao.saveTrash(task.toTrashEntity())
		tasksDao.deleteTaskById(task.id)
	}

	override suspend fun deleteAllTasks() = withContext(ioDispatcher) {
		tasksDao.deleteAllTasks()
	}

	override suspend fun deleteCompletedTasks() = withContext(ioDispatcher) {
		tasksDao.deleteTasksByStatus(ToDoStatus.COMPLETE)
	}
}