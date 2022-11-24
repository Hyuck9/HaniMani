package io.github.hyuck9.hanimani.features.common

import androidx.navigation.NavHostController
import io.github.hyuck9.hanimani.runtime.navigation.SettingsFlow
import io.github.hyuck9.hanimani.runtime.navigation.TasksFlow
import io.github.hyuck9.hanimani.runtime.navigation.navigateSingleTopTo

enum class TabPage {
	Tasks, Settings;

	fun onTabSelected(navController: NavHostController) {
		when (this) {
			Tasks -> { navController.navigateSingleTopTo(TasksFlow.Root.route) }
			Settings -> { navController.navigateSingleTopTo(SettingsFlow.Root.route) }
		}
	}
}