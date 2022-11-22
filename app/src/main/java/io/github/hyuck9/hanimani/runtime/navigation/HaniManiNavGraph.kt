package io.github.hyuck9.hanimani.runtime.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.hyuck9.hanimani.features.common.HaniManiTabBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HaniManiNavGraph(
	modifier: Modifier = Modifier,
	navController: NavHostController = rememberNavController(),
	startDestination: String = TasksFlow.Root.name
) {
	val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

	Scaffold(
		topBar = {
			HaniManiTabBar(
				currentRoute = currentRoute,
				onTabSelected = { it.onTabSelected(navController) }
			)
		}
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = startDestination,
			modifier = modifier.padding(innerPadding)
		) {
			TasksNavHost(navController)
			SettingsNavHost(navController)
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