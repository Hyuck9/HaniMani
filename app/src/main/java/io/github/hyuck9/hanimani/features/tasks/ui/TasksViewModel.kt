package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.common.base.BaseViewModel
import io.github.hyuck9.hanimani.features.tasks.data.ITasksEnvironment
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
	tasksEnvironment: ITasksEnvironment
) : BaseViewModel<TasksState, TasksEffect, TasksAction, ITasksEnvironment>(TasksState(), tasksEnvironment) {

	init {
		viewModelScope.launch {
			environment.getTaskList().collect {
				setState { copy(items = it) }
			}
		}
	}

	override fun dispatch(action: TasksAction) {
		when(action) {
			is TasksAction.OnShow -> {
				viewModelScope.launch {
					setState { copy(taskName = taskName.copy(selection = TextRange(taskName.text.length))) }
				}
			}
			is TasksAction.ClickSubmit -> {
				viewModelScope.launch {
					if (state.value.validTaskName) {
						environment.createTask(state.value.taskName.text.trim())
						setState { copy(taskName = TextFieldValue()) }

						val lastIndexProgressItem = state.value.toDoTaskItems.filterIsInstance<ToDoTaskItem.InProgress>().size
						setEffect( TasksEffect.ScrollTo(lastIndexProgressItem) )
					}
				}
			}
			is TasksAction.ChangeTaskName -> {
				viewModelScope.launch {
					setState { copy(taskName = action.name) }
				}
			}
			is TasksAction.OnToggleStatus -> {
				viewModelScope.launch {
					environment.toggleTaskStatus(action.task)
				}
			}
			is TasksAction.Delete -> {
				viewModelScope.launch {
					environment.deleteTask(action.task)
				}
			}
		}
	}

}