package io.github.hyuck9.hanimani.features.settings.ui

sealed class SettingsAction {
	data class SelectTaskAlign(val selected: TaskAlignItem) : SettingsAction()
	data class SelectFontSize(val selected: FontSizeItem) : SettingsAction()
	data class OnAutorunCheckedChange(val isAutorun: Boolean) : SettingsAction()
	data class OnHideCompleteTasksChange(val isHide: Boolean) : SettingsAction()
}