package io.github.hyuck9.hanimani.runtime.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import io.github.hyuck9.hanimani.common.uicomponent.HaniManiTabBar
import io.github.hyuck9.hanimani.common.uicomponent.HmSnackbar
import io.github.hyuck9.hanimani.common.uiextension.rememberBottomSheetNavigator

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun HaniManiNavGraph() {
	val bottomSheetNavigator = rememberBottomSheetNavigator()
	val bottomSheetConfig = remember { mutableStateOf(DefaultMainBottomSheetConfig) }

	ModalBottomSheetLayout(
		bottomSheetNavigator = bottomSheetNavigator,
		sheetShape = bottomSheetConfig.value.sheetShape,
		scrimColor = if (bottomSheetConfig.value.showScrim) {
			ModalBottomSheetDefaults.scrimColor
		} else {
			Color.Transparent
		}
	) {
		HaniManiNavHost(
			bottomSheetNavigator = bottomSheetNavigator,
			bottomSheetConfig = bottomSheetConfig
		)
	}

}

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HaniManiNavHost(
	bottomSheetNavigator: BottomSheetNavigator,
	bottomSheetConfig: MutableState<HaniManiBottomSheetConfig>,
	startDestination: String = TasksFlow.Root.name
) {
	val navController: NavHostController = rememberNavController(bottomSheetNavigator)
	val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination
	val snackbarHostState = remember { SnackbarHostState() }
	Scaffold(
		topBar = {
			HaniManiTabBar(
				currentRoute = currentRoute,
				onTabSelected = { it.onTabSelected(navController) }
			)
		},
		snackbarHost = {
			SnackbarHost(
				hostState = snackbarHostState,
				snackbar = { snackbarData -> HmSnackbar(snackbarData) }
			)
		},
		modifier = Modifier.statusBarsPadding()
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = startDestination,
			modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
		) {
			TasksNavHost(navController, snackbarHostState, bottomSheetConfig)
			SettingsNavHost(navController, bottomSheetConfig)
		}
	}
}


fun NavHostController.navigateSingleTopTo(route: String) =
	this.navigate(route) {
		popUpTo(
			this@navigateSingleTopTo.graph.findStartDestination().id
		) {
			saveState = true
		}
		launchSingleTop = true
		restoreState = true
	}