package io.github.hyuck9.hanimani.common.data.repository

import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.common.data.local.model.Result
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

	fun getTasksStream(): Flow<Result<List<TaskEntity>>>

	suspend fun getTasks(): Result<List<TaskEntity>>

	fun getTaskStream(taskId: String): Flow<Result<TaskEntity>>

	suspend fun getTask(taskId: String): Result<TaskEntity>

}