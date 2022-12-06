package io.github.hyuck9.hanimani.features.settings.data

import io.github.hyuck9.hanimani.common.data.preference.PreferenceManager
import io.github.hyuck9.hanimani.model.TaskAlign
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsEnvironment @Inject constructor(
	private val preferenceManager: PreferenceManager
) : ISettingsEnvironment {

	override fun getTaskAlign(): Flow<TaskAlign> {
		return preferenceManager.getTaskAlign()
	}

	override suspend fun setTaskAlign(taskAlign: TaskAlign) {
		preferenceManager.setTaskAlign(taskAlign)
	}

}