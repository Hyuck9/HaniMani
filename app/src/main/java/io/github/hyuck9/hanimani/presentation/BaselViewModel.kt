package io.github.hyuck9.hanimani.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job

internal abstract class BaselViewModel: ViewModel() {

	abstract fun fetchData(): Job

}