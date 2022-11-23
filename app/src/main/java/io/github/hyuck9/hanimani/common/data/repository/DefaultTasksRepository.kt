package io.github.hyuck9.hanimani.common.data.repository

import io.github.hyuck9.hanimani.common.data.local.TasksDao
import io.github.hyuck9.hanimani.common.data.local.model.Result
import io.github.hyuck9.hanimani.common.data.local.model.Result.Error
import io.github.hyuck9.hanimani.common.data.local.model.Result.Success
import io.github.hyuck9.hanimani.common.extension.toToDoTask
import io.github.hyuck9.hanimani.common.extension.toToDoTasks
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultTasksRepository(
	private val tasksDao: TasksDao,
	private val ioDispatcher: CoroutineDispatcher
) : TasksRepository {

	override fun getTasksStream(): Flow<Result<List<ToDoTask>>> {
		return tasksDao.observeTasks().map {
			Success(it.toToDoTasks())
		}
	}

	override suspend fun getTasks(): Result<List<ToDoTask>> = withContext(ioDispatcher) {
		return@withContext try {
			Success(tasksDao.getTasks().toToDoTasks())
		} catch (e: Exception) {
			Error(e)
		}
	}

	override fun getTaskStream(taskId: String): Flow<Result<ToDoTask>> {
		return tasksDao.observeTaskById(taskId).map {
			Success(it.toToDoTask())
		}
	}

	override suspend fun getTask(taskId: String): Result<ToDoTask> = withContext(ioDispatcher) {
		return@withContext try {
			val task = tasksDao.getTaskById(taskId)
			if (task != null) {
				Success(task.toToDoTask())
			} else {
				Error(Exception("Task not found!"))
			}
		} catch (e: Exception) {
			Error(e)
		}
	}
}