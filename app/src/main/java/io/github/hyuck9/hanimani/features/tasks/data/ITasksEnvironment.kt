package io.github.hyuck9.hanimani.features.tasks.data

import io.github.hyuck9.hanimani.common.data.local.model.Result
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ITasksEnvironment {
	fun getList(): Flow<Result<List<ToDoTask>>>
}