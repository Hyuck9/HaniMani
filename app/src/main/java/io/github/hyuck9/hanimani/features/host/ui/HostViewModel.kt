package io.github.hyuck9.hanimani.features.host.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.features.base.BaseViewModel
import io.github.hyuck9.hanimani.features.host.data.IHostEnvironment
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(
	hostEnvironment: IHostEnvironment
) : BaseViewModel<HostState, Unit, Unit, IHostEnvironment>(HostState(), hostEnvironment) {

	init {
		initTheme()
	}

	override fun dispatch(action: Unit) {}

	private fun initTheme() {
		viewModelScope.launch {
			environment.getTheme()
				.collect { setState { copy(theme = it) } }
		}
	}
}