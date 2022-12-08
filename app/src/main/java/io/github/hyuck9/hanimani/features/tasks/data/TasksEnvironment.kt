package io.github.hyuck9.hanimani.features.tasks.data

import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.common.data.preference.PreferenceManager
import io.github.hyuck9.hanimani.common.data.repository.TasksRepository
import io.github.hyuck9.hanimani.common.extension.toTaskEntity
import io.github.hyuck9.hanimani.model.FontSize
import io.github.hyuck9.hanimani.model.TaskAlign
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class TasksEnvironment @Inject constructor(
	private val tasksRepository: TasksRepository,
	private val preferenceManager: PreferenceManager
) : ITasksEnvironment {

	override fun getTaskList(): Flow<List<ToDoTask>> = tasksRepository.getTasks()
	override fun getTextAlign(): Flow<TaskAlign> = preferenceManager.getTaskAlign()
	override fun getFontSize(): Flow<FontSize> = preferenceManager.getFontSize()

	override suspend fun createTask(taskName: String, maxOrder: Int) {
		tasksRepository.saveTask(
			ToDoTask(name = taskName, order = maxOrder + 1)
		)
	}

	override suspend fun toggleTaskStatus(task: ToDoTask) {
		val currentDate = LocalDateTime.now()
		task.toggleStatusHandler(
			currentDate = currentDate,
			onUpdateStatus = { completedAt, newStatus ->
				tasksRepository.updateTaskStatus(task.id, newStatus, completedAt, currentDate)
			}
		)
	}

	override suspend fun replaceOrder(fromTask: ToDoTask, toTask: ToDoTask) {
		val currentDate = LocalDateTime.now()
		val fromOrder = fromTask.order
		val toOrder = toTask.order
		tasksRepository.updateOrder(taskId = fromTask.id, order = toOrder, updatedAt = currentDate)
		tasksRepository.updateOrder(taskId = toTask.id, order = fromOrder, updatedAt = currentDate)
	}

	override suspend fun updateOrders(tasks: List<ToDoTask>) {
		val taskEntities: MutableList<TaskEntity> = mutableListOf()
		tasks.forEachIndexed { index, toDoTask ->
			taskEntities.add(toDoTask.toTaskEntity().copy(order = index + 1))
		}
		tasksRepository.updateTasks(taskEntities)
	}

	override suspend fun deleteTask(task: ToDoTask) {
		tasksRepository.deleteTaskById(task.id)
	}

	override suspend fun deleteCompleteTasks() {
		tasksRepository.deleteCompletedTasks()
	}

}