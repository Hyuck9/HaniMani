package io.github.hyuck9.hanimani.runtime.navigation

import androidx.navigation.navArgument
import timber.log.Timber

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

sealed class EditTaskFlow(val name: String) {
	object EditTaskScreen : EditTaskFlow("edit-task-screen") {
		val arguments = listOf(
			navArgument(ARG_TASK_ID) {
				defaultValue = ""
			}
		)

		val route = "$name?$ARG_TASK_ID={$ARG_TASK_ID}"

		fun route(taskId: String): String {
			Timber.tag("TEST").i("taskId : $taskId")
			return "$name?$ARG_TASK_ID=$taskId"
		}
	}
}

sealed class SettingsFlow(val name: String) {
	object Root : SettingsFlow("settings-root") {
		val route = name
	}

	object Settings : SettingsFlow("settings-screen") {
		val route = name
	}

	object Theme : SettingsFlow("theme-screen") {
		val route = name
	}
}

const val ARG_TASK_ID = "taskId"