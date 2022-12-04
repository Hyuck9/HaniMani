package io.github.hyuck9.hanimani.features.theme.data

import io.github.hyuck9.hanimani.common.data.preference.PreferenceManager
import io.github.hyuck9.hanimani.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeEnvironment @Inject constructor(
	private val preferenceManager: PreferenceManager
) : IThemeEnvironment {
	override fun getTheme(): Flow<Theme> {
		return preferenceManager.getTheme()
	}

	override suspend fun setTheme(theme: Theme) {
		preferenceManager.setTheme(theme)
	}
}