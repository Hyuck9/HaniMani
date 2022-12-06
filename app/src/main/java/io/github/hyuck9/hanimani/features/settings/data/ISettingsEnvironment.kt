package io.github.hyuck9.hanimani.features.settings.data

import io.github.hyuck9.hanimani.model.FontSize
import io.github.hyuck9.hanimani.model.TaskAlign
import kotlinx.coroutines.flow.Flow

interface ISettingsEnvironment {
	fun getTaskAlign(): Flow<TaskAlign>
	fun getFontSize(): Flow<FontSize>
	suspend fun setTaskAlign(taskAlign: TaskAlign)
	suspend fun setFontSize(fontSize: FontSize)
}