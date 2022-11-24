package io.github.hyuck9.hanimani.features.tasks.data

import io.github.hyuck9.hanimani.common.data.repository.TasksRepository
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class TasksEnvironment @Inject constructor(
	private val tasksRepository: TasksRepository
) : ITasksEnvironment {

	override fun getTaskList(): Flow<List<ToDoTask>> = tasksRepository.getTasksStream()

	override suspend fun createTask(taskName: String) {
		tasksRepository.saveTask(
			ToDoTask(name = taskName)
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

	override suspend fun deleteTask(task: ToDoTask) {
		tasksRepository.deleteTaskById(task.id)
	}

}