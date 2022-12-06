package io.github.hyuck9.hanimani.features.settings.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.common.base.BaseViewModel
import io.github.hyuck9.hanimani.common.extension.select
import io.github.hyuck9.hanimani.features.settings.data.ISettingsEnvironment
import io.github.hyuck9.hanimani.model.TaskAlign
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
	settingsEnvironment: ISettingsEnvironment
) : BaseViewModel<SettingsState, Unit, SettingsAction, ISettingsEnvironment>(SettingsState(), settingsEnvironment) {

	init {
		initTaskAlign()
	}

	override fun dispatch(action: SettingsAction) {
		when (action) {
			is SettingsAction.SelectTaskAlign -> applyTaskAlign(action.selected)
		}
	}

	private fun initTaskAlign() {
		viewModelScope.launch {
			setState { copy(taskAligns = initialTaskAlign()) }

			environment.getTaskAlign()
				.collect {
					setState { copy(taskAligns = taskAligns.select(it)) }
				}
		}
	}

	private fun applyTaskAlign(item: TaskAlignItem) {
		viewModelScope.launch {
			environment.setTaskAlign(item.taskAlign)
		}
	}

	private fun initialTaskAlign(): List<TaskAlignItem> {
		val data = mutableListOf<TaskAlignItem>()

		data.add( TaskAlignItem(TaskAlign.START, false) )
		data.add( TaskAlignItem(TaskAlign.CENTER, false) )
		data.add( TaskAlignItem(TaskAlign.END, false) )

		return data
	}
}