package io.github.hyuck9.hanimani.features.edittask.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.extension.requestFocusImeAware
import io.github.hyuck9.hanimani.common.uicomponent.HmTodoEditor
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EditTaskScreen(
	viewModel: EditTaskViewModel,
	onCancelClick: () -> Unit,
	onSaveClick: () -> Unit
) {
	val focusRequest = remember { FocusRequester() }
	val state by viewModel.state.collectAsStateWithLifecycle()

	val clipboardManager: ClipboardManager = LocalClipboardManager.current

	LaunchedEffect(Unit) {
		launch { focusRequest.requestFocusImeAware() }
		viewModel.dispatch(EditTaskAction.OnShow)
	}

	HmTodoEditor(
		modifier = Modifier.focusRequester(focusRequest),
		value = state.taskName,
		isValid = state.validTaskName,
		placeholder = stringResource(id = R.string.hint_add_task),
		onValueChange = { viewModel.dispatch(EditTaskAction.ChangeTaskName(it)) },
		onSaveClick = {
			viewModel.dispatch(EditTaskAction.ClickSave)
			onSaveClick()
		},
		onBackClick = onCancelClick,
		onCopyClick = { clipboardManager.setText(AnnotatedString(state.taskName.text)) }
	)

}