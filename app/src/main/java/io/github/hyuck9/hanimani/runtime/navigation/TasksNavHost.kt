package io.github.hyuck9.hanimani.runtime.navigation

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import io.github.hyuck9.hanimani.features.edittask.ui.EditTaskScreen
import io.github.hyuck9.hanimani.features.edittask.ui.EditTaskViewModel
import io.github.hyuck9.hanimani.features.tasks.ui.TaskCreator
import io.github.hyuck9.hanimani.features.tasks.ui.TasksScreen
import io.github.hyuck9.hanimani.features.tasks.ui.TasksViewModel

@Suppress("FunctionName")
fun NavGraphBuilder.TasksNavHost(
	navController: NavHostController,
	bottomSheetConfig: MutableState<HaniManiBottomSheetConfig>
) {
	navigation(startDestination = TasksFlow.TasksScreen.route, route = TasksFlow.Root.route) {
		composable(TasksFlow.TasksScreen.route) {
			val viewModel = hiltViewModel<TasksViewModel>()
			TasksScreen(
				viewModel = viewModel,
				onAddTaskClick = { navController.navigate(TasksFlow.CreateTask.route) },
				onItemClick = { task -> navController.navigate(EditTaskFlow.EditTaskScreen.route(task.id))  }
			)
		}
		TasksBottomSheetNavHost(
			navController = navController,
			bottomSheetConfig = bottomSheetConfig
		)
	}
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Suppress("FunctionName")
private fun NavGraphBuilder.TasksBottomSheetNavHost(
	navController: NavHostController,
	bottomSheetConfig: MutableState<HaniManiBottomSheetConfig>
) {
	bottomSheet(TasksFlow.CreateTask.route) {
		val viewModel = if (navController.previousBackStackEntry != null) {
			hiltViewModel<TasksViewModel>(
				navController.previousBackStackEntry!!
			)
		} else {
			hiltViewModel()
		}
		bottomSheetConfig.value = NoScrimSmallShapeMainBottomSheetConfig
		TaskCreator(viewModel = viewModel)
	}

	bottomSheet(
		route = EditTaskFlow.EditTaskScreen.route,
		arguments = EditTaskFlow.EditTaskScreen.arguments
	) {
		val viewModel = hiltViewModel<EditTaskViewModel>()
		bottomSheetConfig.value = DefaultMainBottomSheetConfig
		EditTaskScreen(
			viewModel = viewModel,
			onCancelClick = { navController.navigateUp() },
			onSaveClick = { navController.navigateUp() }
		)
	}
}