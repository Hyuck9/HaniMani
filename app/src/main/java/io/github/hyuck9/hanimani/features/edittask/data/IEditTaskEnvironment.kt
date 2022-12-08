package io.github.hyuck9.hanimani.features.edittask.data

import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface IEditTaskEnvironment {
	fun getTaskById(taskId: String): Flow<ToDoTask>
	suspend fun updateTask(taskId: String, name: String)
}