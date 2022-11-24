package io.github.hyuck9.hanimani.common.data.repository

import io.github.hyuck9.hanimani.common.data.local.model.Result
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

	fun getTasksStream(): Flow<List<ToDoTask>>

	suspend fun getTasks(): Result<List<ToDoTask>>

	fun getTaskStream(taskId: String): Flow<Result<ToDoTask>>

	suspend fun getTask(taskId: String): Result<ToDoTask>

	suspend fun saveTask(task: ToDoTask)

}