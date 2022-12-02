package io.github.hyuck9.hanimani.features.tasks.data

import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ITasksEnvironment {
	fun getTaskList(): Flow<List<ToDoTask>>
	suspend fun createTask(taskName: String, maxOrder: Int)
	suspend fun toggleTaskStatus(task: ToDoTask)
	suspend fun deleteTask(task: ToDoTask)
	suspend fun deleteCompleteTasks()
	suspend fun replaceOrder(fromTask: ToDoTask, toTask: ToDoTask)
	suspend fun updateOrders(tasks: List<ToDoTask>)
}