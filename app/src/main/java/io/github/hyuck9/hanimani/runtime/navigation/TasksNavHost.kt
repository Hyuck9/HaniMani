package io.github.hyuck9.hanimani.runtime.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.hyuck9.hanimani.features.tasks.ui.TasksScreen
import io.github.hyuck9.hanimani.features.tasks.ui.TasksViewModel

@Suppress("FunctionName")
fun NavGraphBuilder.TasksNavHost(
	navController: NavHostController
) {
	navigation(startDestination = TasksFlow.TasksScreen.route, route = TasksFlow.Root.route) {
		composable(TasksFlow.TasksScreen.route) {
			val viewModel = hiltViewModel<TasksViewModel>()
			TasksScreen(viewModel = viewModel)
		}
	}
}