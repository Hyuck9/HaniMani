package io.github.hyuck9.hanimani.common.data.repository

import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.model.ToDoStatus
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface TasksRepository {

	fun getTasks(): Flow<List<ToDoTask>>

	fun getTaskById(taskId: String): Flow<ToDoTask>


	suspend fun saveTask(task: ToDoTask)

	suspend fun updateTaskStatus(
		taskId: String,
		status: ToDoStatus,
		completedAt: LocalDateTime?,
		updatedAt: LocalDateTime
	)

	suspend fun updateOrder(
		taskId: String,
		order: Int,
		updatedAt: LocalDateTime
	)
	suspend fun updateTasks(tasks: List<TaskEntity>)

	suspend fun updateTaskName(
		taskId: String,
		name: String,
		updatedAt: LocalDateTime
	)

	suspend fun deleteTaskById(taskId: String)
	suspend fun deleteAllTasks()
	suspend fun deleteCompletedTasks()
}