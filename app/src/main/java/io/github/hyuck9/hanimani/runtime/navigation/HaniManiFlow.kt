package io.github.hyuck9.hanimani.runtime.navigation

sealed class TasksFlow(val name: String) {
	object Root : TasksFlow("tasks-root") {
		val route = name
	}

	object TasksScreen : TasksFlow("tasks-screen") {
		val route = name
	}

	object CreateTask : TasksFlow("create-task-screen") {
		val route = name
	}
}

sealed class SettingsFlow(val name: String) {
	object Root : SettingsFlow("settings-root") {
		val route = name
	}

	object Settings : SettingsFlow("settings-screen") {
		val route = name
	}
}