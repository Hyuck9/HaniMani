package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.compose.ui.text.input.TextFieldValue

sealed class TasksAction {
	object OnShow : TasksAction()
	data class ChangeTaskName(val name: TextFieldValue) : TasksAction()
}