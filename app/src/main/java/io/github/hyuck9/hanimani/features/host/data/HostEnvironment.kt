package io.github.hyuck9.hanimani.features.host.data

import io.github.hyuck9.hanimani.common.data.preference.PreferenceManager
import io.github.hyuck9.hanimani.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HostEnvironment @Inject constructor(
	private val preferenceManager: PreferenceManager
) : IHostEnvironment {

	override fun getTheme(): Flow<Theme> = preferenceManager.getTheme()
}