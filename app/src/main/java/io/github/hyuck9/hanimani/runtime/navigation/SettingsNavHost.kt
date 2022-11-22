package io.github.hyuck9.hanimani.runtime.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.hyuck9.hanimani.features.settings.ui.SettingsScreen

@Suppress("FunctionName")
fun NavGraphBuilder.SettingsNavHost(
	navController: NavHostController
) {
	navigation(startDestination = SettingsFlow.Settings.route, route = SettingsFlow.Root.route) {
		composable(SettingsFlow.Settings.route) {
			SettingsScreen()
		}
	}
}