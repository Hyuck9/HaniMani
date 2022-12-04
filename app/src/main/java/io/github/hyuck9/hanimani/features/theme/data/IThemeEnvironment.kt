package io.github.hyuck9.hanimani.features.theme.data

import io.github.hyuck9.hanimani.model.Theme
import kotlinx.coroutines.flow.Flow

interface IThemeEnvironment {
	fun getTheme(): Flow<Theme>
	suspend fun setTheme(theme: Theme)
}