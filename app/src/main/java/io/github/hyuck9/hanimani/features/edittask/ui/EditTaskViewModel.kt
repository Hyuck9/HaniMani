package io.github.hyuck9.hanimani.features.edittask.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.common.base.BaseViewModel
import io.github.hyuck9.hanimani.features.edittask.data.IEditTaskEnvironment
import io.github.hyuck9.hanimani.runtime.navigation.ARG_TASK_ID
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	editTaskEnvironment: IEditTaskEnvironment,
) : BaseViewModel<EditTaskState, Unit, EditTaskAction, IEditTaskEnvironment>(EditTaskState(), editTaskEnvironment) {

	private val taskId = savedStateHandle.get<String>(ARG_TASK_ID)

	init {
		initTask()
	}

	private fun initTask() {
		viewModelScope.launch {
			if (taskId != null) {
				environment.getTaskById(taskId)
					.collect {
						setState { copy(task = it) }
					}
			}
		}
	}

	override fun dispatch(action: EditTaskAction) {
		when (action) {
			is EditTaskAction.OnShow -> {
				viewModelScope.launch {
					delay(50)
					setState {
						copy(
							taskName = taskName.copy(
								text = task.name,
								selection = TextRange(task.name.length)
							)
						)
					}
				}
			}
			is EditTaskAction.ChangeTaskName -> {
				viewModelScope.launch {
					setState { copy(taskName = action.name) }
				}
			}
			is EditTaskAction.ClickSave -> {
				viewModelScope.launch {
					environment.updateTask(state.value.task.id, state.value.taskName.text.trim())
					setState { copy(taskName = TextFieldValue()) }
				}
			}
		}
	}


}