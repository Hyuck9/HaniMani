package io.github.hyuck9.hanimani.features.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * [STATE] : 각 feature 별 로직 수행 및 UI 렌더링에 필요한 데이터. (feature의 수명주기동안 persistence)
 * [EFFECT] : [STATE]와 비슷하지만 persistence 하지 않음.(navigation 처리, toast 표시, snackbar 표시 등)
 * [ACTION] : 사용자 action 및 noti / event 등 상태를 변경하는 모든 작업 (usecase 묶음)
 * [ENVIRONMENT] : feature에 필요한 모든 dependency를 보유. (UI와 Data 계증을 분리하기 위해 필요한 타입)
 */
abstract class BaseViewModel<STATE, EFFECT, ACTION, ENVIRONMENT>(
	initialState: STATE,
	protected val environment: ENVIRONMENT
) : ViewModel() {

	private val _state = MutableStateFlow(initialState)
	private val _effect: MutableStateFlow<EFFECT?> = MutableStateFlow(null)

	val state: StateFlow<STATE> = _state.asStateFlow()
	val effect: StateFlow<EFFECT?> = _effect.asStateFlow()

	abstract fun dispatch(action: ACTION)

	protected fun setState(newState: STATE.() -> STATE) {
		_state.update(newState)
	}
	protected fun setEffect(newEFFECT: EFFECT) {
		_effect.update { newEFFECT }
	}

	fun resetEffect() {
		_effect.update { null }
	}

}