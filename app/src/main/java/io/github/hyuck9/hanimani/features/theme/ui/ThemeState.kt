package io.github.hyuck9.hanimani.features.theme.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import io.github.hyuck9.hanimani.model.Theme

@Immutable
data class ThemeState(
	val items: List<ThemeItem> = listOf()
)

data class ThemeItem(
	@StringRes val title: Int,
	val theme: Theme,
	val brush: Brush,
	val applied: Boolean
)