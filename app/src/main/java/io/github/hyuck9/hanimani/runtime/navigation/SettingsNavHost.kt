package io.github.hyuck9.hanimani.runtime.navigation

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import io.github.hyuck9.hanimani.features.settings.ui.SettingsScreen
import io.github.hyuck9.hanimani.features.theme.ui.ThemeScreen
import io.github.hyuck9.hanimani.features.theme.ui.ThemeViewModel

@OptIn(ExperimentalMaterialNavigationApi::class)
@Suppress("FunctionName")
fun NavGraphBuilder.SettingsNavHost(
	navController: NavHostController,
	bottomSheetConfig: MutableState<HaniManiBottomSheetConfig>
) {
	navigation(startDestination = SettingsFlow.Settings.route, route = SettingsFlow.Root.route) {
		composable(SettingsFlow.Settings.route) {
			SettingsScreen(
				onClickTheme = { navController.navigate(SettingsFlow.Theme.route) }
			)
		}
		bottomSheet(SettingsFlow.Theme.route) {
			val viewModel = hiltViewModel<ThemeViewModel>()
			bottomSheetConfig.value = DefaultMainBottomSheetConfig
			ThemeScreen(
				viewModel = viewModel,
				onClickBack = { navController.navigateUp() }
			)
		}
	}
}