package io.github.hyuck9.hanimani.features.tasks.data

import io.github.hyuck9.hanimani.common.data.local.model.Result
import io.github.hyuck9.hanimani.common.data.repository.TasksRepository
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksEnvironment @Inject constructor(
	private val tasksRepository: TasksRepository
) : ITasksEnvironment {

	override fun getList(): Flow<Result<List<ToDoTask>>> = tasksRepository.getTasksStream()

}