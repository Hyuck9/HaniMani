package io.github.hyuck9.hanimani.features.tasks.data

import io.github.hyuck9.hanimani.model.FontSize
import io.github.hyuck9.hanimani.model.TaskAlign
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ITasksEnvironment {
	fun getTaskList(): Flow<List<ToDoTask>>
	fun getTextAlign(): Flow<TaskAlign>
	fun getFontSize(): Flow<FontSize>
	suspend fun createTask(taskName: String, maxOrder: Int)
	suspend fun toggleTaskStatus(task: ToDoTask)
	suspend fun deleteTask(task: ToDoTask)
	suspend fun restoreTask(task: ToDoTask)
	suspend fun deleteCompleteTasks()
	suspend fun replaceOrder(fromTask: ToDoTask, toTask: ToDoTask)
	suspend fun updateOrders(tasks: List<ToDoTask>)
}