package io.github.hyuck9.hanimani.common.data.repository

import io.github.hyuck9.hanimani.common.data.local.TasksDao
import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.common.data.local.model.Result
import io.github.hyuck9.hanimani.common.data.local.model.Result.Success
import io.github.hyuck9.hanimani.common.data.local.model.Result.Error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultTasksRepository(
	private val tasksDao: TasksDao,
	private val ioDispatcher: CoroutineDispatcher
) : TasksRepository {

	override fun getTasksStream(): Flow<Result<List<TaskEntity>>> {
		return tasksDao.observeTasks().map {
			Success(it)
		}
	}

	override suspend fun getTasks(): Result<List<TaskEntity>> = withContext(ioDispatcher) {
		return@withContext try {
			Success(tasksDao.getTasks())
		} catch (e: Exception) {
			Error(e)
		}
	}

	override fun getTaskStream(taskId: String): Flow<Result<TaskEntity>> {
		return tasksDao.observeTaskById(taskId).map {
			Success(it)
		}
	}

	override suspend fun getTask(taskId: String): Result<TaskEntity> = withContext(ioDispatcher) {
		return@withContext try {
			val task = tasksDao.getTaskById(taskId)
			if (task != null) {
				Success(task)
			} else {
				Error(Exception("Task not found!"))
			}
		} catch (e: Exception) {
			Error(e)
		}
	}
}