package io.github.hyuck9.hanimani.features.tasks.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.extension.isScrollingUp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
	viewModel: TasksViewModel
) {

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
		Text(
			text = "Hello HaniMani!!!!",
			modifier = Modifier.padding(padding)
		)
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