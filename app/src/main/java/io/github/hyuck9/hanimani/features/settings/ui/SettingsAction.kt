package io.github.hyuck9.hanimani.features.settings.ui

sealed class SettingsAction {
	data class SelectTaskAlign(val selected: TaskAlignItem) : SettingsAction()
}