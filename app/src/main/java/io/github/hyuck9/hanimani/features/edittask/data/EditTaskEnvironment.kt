package io.github.hyuck9.hanimani.features.edittask.data

import io.github.hyuck9.hanimani.common.data.repository.TasksRepository
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class EditTaskEnvironment @Inject constructor(
	private val tasksRepository: TasksRepository
) : IEditTaskEnvironment {

	override fun getTaskById(taskId: String): Flow<ToDoTask> = tasksRepository.getTaskById(taskId)

	override suspend fun updateTask(taskId: String, name: String) {
		val currentDate = LocalDateTime.now()
		tasksRepository.updateTaskName(taskId, name, currentDate)
	}

}