package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.text.input.TextFieldValue
import io.github.hyuck9.hanimani.common.uicomponent.reorder.ItemPosition
import io.github.hyuck9.hanimani.model.ToDoTask

sealed class TasksAction {
	object OnShow : TasksAction()
	object ClickSubmit : TasksAction()
	object OnCompletedTasksDelete : TasksAction()
	data class ChangeTaskName(val name: TextFieldValue) : TasksAction()
	data class OnToggleStatus(val task: ToDoTask) : TasksAction()
	data class Delete(val task: ToDoTask, val snackbarHostState: SnackbarHostState) : TasksAction()
	data class OnUndoDeleteSnackBar(val task: ToDoTask, val snackbarHostState: SnackbarHostState) : TasksAction()
	data class ReplaceOrder(val from: ItemPosition, val to: ItemPosition) : TasksAction()
	object DragEnd : TasksAction()
}