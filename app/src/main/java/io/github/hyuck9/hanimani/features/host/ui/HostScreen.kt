package io.github.hyuck9.hanimani.features.host.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HostScreen(content: @Composable () -> Unit) {
	val viewModel = hiltViewModel<HostViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()

	HaniManiTheme(
		theme = state.theme,
		content = content
	)
}