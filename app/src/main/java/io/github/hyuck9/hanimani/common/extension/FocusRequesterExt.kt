package io.github.hyuck9.hanimani.common.extension

import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.delay

suspend fun FocusRequester.requestFocusImeAware() {
	delay(200)
	requestFocus()
}
