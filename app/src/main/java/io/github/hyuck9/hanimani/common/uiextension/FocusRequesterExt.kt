package io.github.hyuck9.hanimani.common.uiextension

import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.delay

suspend fun FocusRequester.requestFocusImeAware() {
	delay(10)
	requestFocus()
}
