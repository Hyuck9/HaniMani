package io.github.hyuck9.hanimani.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.hyuck9.hanimani.common.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun <STATE, EFFECT, ACTION, ENVIRONMENT> HandleEffect(
	viewModel: BaseViewModel<STATE, EFFECT, ACTION, ENVIRONMENT>,
	handle: suspend CoroutineScope.(EFFECT) -> Unit
) {
	val effect by viewModel.effect.collectAsStateWithLifecycle()
	LaunchedEffect(effect) {
		effect?.let {
			handle(it)
			viewModel.resetEffect()
		}
	}
}