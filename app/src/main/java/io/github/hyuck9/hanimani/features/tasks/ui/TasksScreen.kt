package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.extension.*
import io.github.hyuck9.hanimani.common.preview.SampleBooleanProvider
import io.github.hyuck9.hanimani.common.theme.AlphaDisabled
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.common.uicomponent.*
import io.github.hyuck9.hanimani.model.ToDoTask
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun TasksScreen(
	viewModel: TasksViewModel,
	onAddTaskClick: () -> Unit,
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	val lazyListState = rememberLazyListState()

	HandleEffect(viewModel) {
		when (it) {
			is TasksEffect.ScrollTo -> {
				val position = it.position
				lazyListState.animateScrollToItem(position)
			}
		}
	}

	Scaffold(
		floatingActionButton = {
			AddTaskButton(
				extended = lazyListState.isScrollingUp(),
				onClick = onAddTaskClick
			)
		}
	) { padding ->
		TasksContent(
			modifier = Modifier.padding(bottom = padding.calculateBottomPadding()),
			tasks = state.toDoTaskItems,
			onClick = {},
			onCheckboxClick = { viewModel.dispatch(TasksAction.OnToggleStatus(it)) },
			onSwipeToDelete = { viewModel.dispatch(TasksAction.Delete(it)) },
			onAllCompleteTasksDelete = { viewModel.dispatch(TasksAction.OnCompletedTasksDelete) },
			listState = lazyListState
		)
	}
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TasksContent(
	modifier: Modifier,
	tasks: List<ToDoTaskItem>,
	onClick: (ToDoTask) -> Unit,
	onCheckboxClick: (ToDoTask) -> Unit,
	onSwipeToDelete: (ToDoTask) -> Unit,
	onAllCompleteTasksDelete: () -> Unit,
	color: Color = MaterialTheme.colorScheme.primary,
	listState: LazyListState
) {
	val coroutineScope = rememberCoroutineScope()

	LazyColumn(
		modifier = modifier.fillMaxSize()
			.background(MaterialTheme.colorScheme.background),
		state = listState
	) {
		if (tasks.isEmpty()) {
			item {
				EmptyTaskTipText(
					modifier = Modifier
						.fillParentMaxHeight()
						.padding(bottom = 100.dp)
				)
			}
		} else {
			items(
				items = tasks,
				key = { item -> item.identifier() }
			) { item ->
				when (item) {
					is ToDoTaskItem.CompleteHeader -> {
						CompleteHeader(
							color = color,
							onAllCompleteTasksDelete = onAllCompleteTasksDelete
						)
					}
					is ToDoTaskItem.Complete -> {
						HmToDoItemCell(
							modifier = Modifier.animateItemPlacement(),
							name = item.toDoTask.name,
							checkboxColor = color.copy(alpha = AlphaDisabled),
							contentPaddingValues = PaddingValues(all = 8.dp),
							leftIcon = Icons.Rounded.CheckCircle,
							textDecoration = TextDecoration.LineThrough,
							onClick = { onClick(item.toDoTask) },
							onSwipeToDelete = { onSwipeToDelete(item.toDoTask) },
							onCheckboxClick = { onCheckboxClick(item.toDoTask) }
						)
					}
					is ToDoTaskItem.InProgress -> {
						var isChecked by remember { mutableStateOf(false) }
						var debounceJob: Job? by remember { mutableStateOf(null) }

						HmToDoItemCell(
							modifier = Modifier.animateItemPlacement(),
							name = item.toDoTask.name,
							checkboxColor = color,
							contentPaddingValues = PaddingValues(vertical = 24.dp, horizontal = 8.dp),
							leftIcon = if (isChecked) {
								Icons.Rounded.CheckCircle
							} else {
								Icons.Rounded.RadioButtonUnchecked
							},
							textDecoration = TextDecoration.None,
							onClick = { onClick(item.toDoTask) },
							onSwipeToDelete = { onSwipeToDelete(item.toDoTask) },
							onCheckboxClick = {
								isChecked = !isChecked
								debounceJob?.cancel()
								if (isChecked) {
									debounceJob = coroutineScope.launch {
										delay(300)
										onCheckboxClick(item.toDoTask)
									}
								}
							}
						)
					}
				}
			}
		}
	}
}

@Composable
private fun CompleteHeader(
	modifier: Modifier = Modifier,
	onAllCompleteTasksDelete: () -> Unit,
	color: Color = MaterialTheme.colorScheme.primary
) {
	Spacer(Modifier.height(16.dp))
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween,
		modifier = modifier
			.padding(horizontal = 16.dp)
			.fillMaxWidth()
	) {
		Text(
			text = stringResource(R.string.header_task_completed),
			style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
			color = color
		)

		TextButton(
			onClick = { onAllCompleteTasksDelete() },
			shape = CircleShape,
			colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
		) {
			Text(
				text = stringResource(R.string.button_all_completed_task),
				style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
				color = MaterialTheme.colorScheme.error
			)
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
				imageVector = Icons.Default.AddTask,
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

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TaskCreator(
	viewModel: TasksViewModel
) {
	val focusRequest = remember { FocusRequester() }
	val state by viewModel.state.collectAsStateWithLifecycle()

	LaunchedEffect(Unit) {
		launch { focusRequest.requestFocusImeAware() }
		 viewModel.dispatch(TasksAction.OnShow)
	}

	HmTodoCreator(
		value = state.taskName,
		modifier = Modifier.focusRequester(focusRequest),
		isValid = state.validTaskName,
		placeholder = stringResource(id = R.string.hint_add_task),
		onValueChange = { viewModel.dispatch(TasksAction.ChangeTaskName(it)) },
		onSubmit = { viewModel.dispatch(TasksAction.ClickSubmit) }
	)
}



@Preview(showBackground = true)
@Composable
private fun AddTaskButtonPreview(@PreviewParameter(SampleBooleanProvider::class) extended: Boolean) {
	HaniManiTheme {
		AddTaskButton(
			extended = extended,
			onClick = {}
		)
	}
}