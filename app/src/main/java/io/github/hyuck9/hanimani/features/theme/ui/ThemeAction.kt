package io.github.hyuck9.hanimani.features.theme.ui

sealed class ThemeAction {
	data class SelectTheme(val selected: ThemeItem) : ThemeAction()
}