package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.common.base.BaseViewModel
import io.github.hyuck9.hanimani.features.tasks.data.ITasksEnvironment
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
	tasksEnvironment: ITasksEnvironment
) : BaseViewModel<TasksState, Unit, TasksAction, ITasksEnvironment>(TasksState(), tasksEnvironment) {

	init {
		viewModelScope.launch {

		}
	}

	override fun dispatch(action: TasksAction) {
		when(action) {
			else -> {}
		}
	}

}