package io.github.hyuck9.hanimani.features.tasks.data

import io.github.hyuck9.hanimani.common.data.local.model.Result
import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import kotlinx.coroutines.flow.Flow

interface ITasksEnvironment {
	fun getList(): Flow<Result<List<TaskEntity>>>
}