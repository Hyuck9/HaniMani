package io.github.hyuck9.hanimani.features.host.data

import io.github.hyuck9.hanimani.model.Theme
import kotlinx.coroutines.flow.Flow

interface IHostEnvironment {
	fun getTheme(): Flow<Theme>
}