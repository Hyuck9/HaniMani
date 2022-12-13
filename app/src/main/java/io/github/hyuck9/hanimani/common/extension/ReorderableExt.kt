package io.github.hyuck9.hanimani.common.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.util.fastFirstOrNull
import io.github.hyuck9.hanimani.common.uicomponent.reorder.ReorderableState

@SuppressLint("ReturnFromAwaitPointerEventScope")
fun Modifier.reorderable(
	state: ReorderableState<*>
) = then(
	Modifier.pointerInput(Unit) {
		@Suppress("DEPRECATION")
		forEachGesture {
			val dragStart = state.interactions.receive()
			val down = awaitPointerEventScope {
				currentEvent.changes.fastFirstOrNull { it.id == dragStart.id }
			}
			if (down != null && state.onDragStart(down.position.x.toInt(), down.position.y.toInt())) {
				dragStart.offset?.apply {
					state.onDrag(x.toInt(), y.toInt())
				}
				detectDrag(
					down.id,
					onDragEnd = { state.onDragCanceled() },
					onDragCancel = { state.onDragCanceled() },
					onDrag = { change, dragAmount ->
						change.consume()
						state.onDrag(dragAmount.x.toInt(), dragAmount.y.toInt())
					})
			}
		}
	}
)

internal suspend fun PointerInputScope.detectDrag(
	down: PointerId,
	onDragEnd: () -> Unit = { },
	onDragCancel: () -> Unit = { },
	onDrag: (change: PointerInputChange, dragAmount: Offset) -> Unit,
) {
	awaitPointerEventScope {
		if (
			drag(down) {
				onDrag(it, it.positionChange())
				it.consume()
			}
		) {
			// consume up if we quit drag gracefully with the up
			currentEvent.changes.forEach {
				if (it.changedToUp()) it.consume()
			}
			onDragEnd()
		} else {
			onDragCancel()
		}
	}
}

internal data class StartDrag(val id: PointerId, val offset: Offset? = null)