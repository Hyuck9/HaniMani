package io.github.hyuck9.hanimani.features.settings.data

import io.github.hyuck9.hanimani.model.TaskAlign
import kotlinx.coroutines.flow.Flow

interface ISettingsEnvironment {
	fun getTaskAlign(): Flow<TaskAlign>
	suspend fun setTaskAlign(taskAlign: TaskAlign)
}