package io.github.hyuck9.hanimani.common.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import io.github.hyuck9.hanimani.common.uicomponent.reorder.ReorderableState

fun Modifier.detectReorder(state: ReorderableState<*>) =
	this.then(
		Modifier.pointerInput(Unit) {
			@Suppress("DEPRECATION")
			forEachGesture {
				awaitPointerEventScope {
					val down = awaitFirstDown(requireUnconsumed = false)
					var drag: PointerInputChange?
					var overSlop = Offset.Zero
					do {
						drag = awaitPointerSlopOrCancellation(down.id, down.type) { change, over ->
							change.consume()
							overSlop = over
						}
					} while (drag != null && !drag.isConsumed)
					if (drag != null) {
						state.interactions.trySend(StartDrag(down.id, overSlop))
					}
				}
			}
		}
	)

@SuppressLint("ReturnFromAwaitPointerEventScope")
fun Modifier.detectReorderAfterLongPress(state: ReorderableState<*>) =
	this.then(
		Modifier.pointerInput(Unit) {
			@Suppress("DEPRECATION")
			forEachGesture {
				val down = awaitPointerEventScope {
					awaitFirstDown(requireUnconsumed = false)
				}
				awaitLongPressOrCancellation(down)?.also {
					state.interactions.trySend(StartDrag(down.id))
				}
			}
		}
	)