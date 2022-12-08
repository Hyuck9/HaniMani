package io.github.hyuck9.hanimani.features.edittask.ui

import androidx.compose.ui.text.input.TextFieldValue

sealed class EditTaskAction {
	object OnShow : EditTaskAction()
	data class ChangeTaskName(val name: TextFieldValue) : EditTaskAction()
	object ClickSave : EditTaskAction()
}