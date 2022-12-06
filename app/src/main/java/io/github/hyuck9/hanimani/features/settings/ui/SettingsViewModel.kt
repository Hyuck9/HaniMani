package io.github.hyuck9.hanimani.features.settings.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.common.base.BaseViewModel
import io.github.hyuck9.hanimani.common.extension.select
import io.github.hyuck9.hanimani.features.settings.data.ISettingsEnvironment
import io.github.hyuck9.hanimani.model.FontSize
import io.github.hyuck9.hanimani.model.TaskAlign
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
	settingsEnvironment: ISettingsEnvironment
) : BaseViewModel<SettingsState, Unit, SettingsAction, ISettingsEnvironment>(
	SettingsState(),
	settingsEnvironment
) {

	init {
		initTaskAlign()
		initFontSize()
	}

	override fun dispatch(action: SettingsAction) {
		when (action) {
			is SettingsAction.SelectTaskAlign -> applyTaskAlign(action.selected)
			is SettingsAction.SelectFontSize -> applyFontSize(action.selected)
		}
	}

	private fun initTaskAlign() {
		viewModelScope.launch {
			setState { copy(taskAligns = initialTaskAligns()) }

			environment.getTaskAlign()
				.collect {
					setState { copy(taskAligns = taskAligns.select(it)) }
				}
		}
	}

	private fun initFontSize() {
		viewModelScope.launch {
			setState { copy(fontSizes = initialFontSizes()) }

			environment.getFontSize()
				.collect {
					setState { copy(fontSizes = fontSizes.select(it)) }
				}
		}
	}

	private fun applyTaskAlign(item: TaskAlignItem) {
		viewModelScope.launch {
			environment.setTaskAlign(item.taskAlign)
		}
	}

	private fun applyFontSize(item: FontSizeItem) {
		viewModelScope.launch {
			environment.setFontSize(item.fontSize)
		}
	}

	private fun initialTaskAligns(): List<TaskAlignItem> = listOf(
		TaskAlignItem(TaskAlign.START, false),
		TaskAlignItem(TaskAlign.CENTER, false),
		TaskAlignItem(TaskAlign.END, false)
	)

	private fun initialFontSizes(): List<FontSizeItem> = listOf(
		FontSizeItem(FontSize.SMALL, false),
		FontSizeItem(FontSize.MEDIUM, false),
		FontSizeItem(FontSize.LARGE, false)
	)
}