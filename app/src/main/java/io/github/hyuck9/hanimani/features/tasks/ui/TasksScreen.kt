package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.extension.identifier
import io.github.hyuck9.hanimani.common.extension.isScrollingUp
import io.github.hyuck9.hanimani.common.uicomponent.EmptyTaskTipText
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun TasksScreen(
	viewModel: TasksViewModel
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	val lazyListState = rememberLazyListState()
	val coroutineScope = rememberCoroutineScope()

	Scaffold(
		floatingActionButton = {
			AddTaskButton(
				extended = lazyListState.isScrollingUp(),
				onClick = {
					coroutineScope.launch {
						// TODO: 할일 추가
					}
				}
			)
		}
	) { padding ->
		TasksContent(
			modifier = Modifier.padding(padding),
			tasks = state.items,
			onClick = {},
			onCheckClick = {},
			onSwipeToDelete = {},
			listState = lazyListState
		)
	}
}

@Composable
fun TasksContent(
	modifier: Modifier,
	tasks: List<ToDoTaskItem>,
	onClick: (ToDoTask) -> Unit,
	onCheckClick: (ToDoTask) -> Unit,
	onSwipeToDelete: (ToDoTask) -> Unit,
	listState: LazyListState
) {
	LazyColumn(
		modifier = modifier.fillMaxSize(),
		state = listState
	) {
		if (tasks.isEmpty()) {
			item {
				EmptyTaskTipText(
					modifier = Modifier.fillParentMaxHeight()
						.padding(bottom = 100.dp)
				)
			}
		} else {
			items(
				items = tasks,
				key = { item -> item.identifier() }
			) { lazyItemScope ->
				when (lazyItemScope) {
					is ToDoTaskItem.CompleteHeader -> {
						// TODO: 완료 헤더
					}
					is ToDoTaskItem.Complete -> {
						// TODO: 완료한 할일
					}
					is ToDoTaskItem.InProgress -> {
						// TODO: 진행중인 할일
					}
				}
			}
		}
	}
}

@Composable
private fun AddTaskButton(
	extended: Boolean,
	onClick: () -> Unit
) {
	FloatingActionButton(onClick = onClick) {
		Row(
			modifier = Modifier.padding(horizontal = 16.dp)
		) {
			Icon(
				imageVector = Icons.Default.Add,
				contentDescription = stringResource(id = R.string.button_add_task)
			)

			AnimatedVisibility(visible = extended) {
				Text(
					text = stringResource(id = R.string.button_add_task),
					modifier = Modifier.padding(start = 8.dp, top = 3.dp)
				)
			}
		}
	}
}




@Preview(showBackground = true)
@Composable
fun AddTaskButtonPreview(@PreviewParameter(SampleExtendedProvider::class) extended: Boolean) {
	AddTaskButton(
		extended = extended,
		onClick = {}
	)
}

private class SampleExtendedProvider : PreviewParameterProvider<Boolean> {
	override val values: Sequence<Boolean> = sequenceOf(true, false)
}