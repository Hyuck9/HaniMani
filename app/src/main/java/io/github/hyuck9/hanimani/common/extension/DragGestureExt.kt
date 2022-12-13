package io.github.hyuck9.hanimani.common.extension

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastAll
import androidx.compose.ui.util.fastAny
import androidx.compose.ui.util.fastFirstOrNull
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout

internal suspend fun AwaitPointerEventScope.awaitPointerSlopOrCancellation(
	pointerId: PointerId,
	pointerType: PointerType,
	onPointerSlopReached: (change: PointerInputChange, overSlop: Offset) -> Unit
): PointerInputChange? {
	if (currentEvent.isPointerUp(pointerId)) {
		return null // The pointer has already been lifted, so the gesture is canceled
	}
	var offset = Offset.Zero
	val touchSlop = viewConfiguration.pointerSlop(pointerType)

	var pointer = pointerId

	while (true) {
		val event = awaitPointerEvent()
		val dragEvent = event.changes.fastFirstOrNull { it.id == pointer } ?: return null
		if (dragEvent.isConsumed) {
			return null
		} else if (dragEvent.changedToUpIgnoreConsumed()) {
			val otherDown = event.changes.fastFirstOrNull { it.pressed }
			if (otherDown == null) {
				// This is the last "up"
				return null
			} else {
				pointer = otherDown.id
			}
		} else {
			offset += dragEvent.positionChange()
			val distance = offset.getDistance()
			if (distance >= touchSlop) {
				val touchSlopOffset = offset / distance * touchSlop
				onPointerSlopReached(dragEvent, offset - touchSlopOffset)
				offset = Offset.Zero
			}
			awaitPointerEvent(PointerEventPass.Final)
		}
	}
}

internal suspend fun PointerInputScope.awaitLongPressOrCancellation(
	initialDown: PointerInputChange
): PointerInputChange? {
	var longPress: PointerInputChange? = null
	var currentDown = initialDown
	val longPressTimeout = viewConfiguration.longPressTimeoutMillis
	return try {
		// wait for first tap up or long press
		withTimeout(longPressTimeout) {
			awaitPointerEventScope {
				var finished = false
				while (!finished) {
					val event = awaitPointerEvent(PointerEventPass.Main)
					if (event.changes.fastAll { it.changedToUpIgnoreConsumed() }) {
						// All pointers are up
						finished = true
					}

					if (
						event.changes.fastAny {
							it.isConsumed || it.isOutOfBounds(size, extendedTouchPadding)
						}
					) {
						finished = true // Canceled
					}

					// Check for cancel by position consumption. We can look on the Final pass of
					// the existing pointer event because it comes after the Main pass we checked
					// above.
					val consumeCheck = awaitPointerEvent(PointerEventPass.Final)
					if (consumeCheck.changes.fastAny { it.isConsumed }) {
						finished = true
					}
					if (!event.isPointerUp(currentDown.id)) {
						longPress = event.changes.fastFirstOrNull { it.id == currentDown.id }
					} else {
						val newPressed = event.changes.fastFirstOrNull { it.pressed }
						if (newPressed != null) {
							currentDown = newPressed
							longPress = currentDown
						} else {
							// should technically never happen as we checked it above
							finished = true
						}
					}
				}
			}
		}
		null
	} catch (_: TimeoutCancellationException) {
		longPress ?: initialDown
	}
}

private fun PointerEvent.isPointerUp(pointerId: PointerId): Boolean =
	changes.fastFirstOrNull { it.id == pointerId }?.pressed != true

// This value was determined using experiments and common sense.
// We can't use zero slop, because some hypothetical desktop/mobile devices can send
// pointer events with a very high precision (but I haven't encountered any that send
// events with less than 1px precision)
private val mouseSlop = 0.125.dp
private val defaultTouchSlop = 18.dp // The default touch slop on Android devices
private val mouseToTouchSlopRatio = mouseSlop / defaultTouchSlop

// TODO(demin): consider this as part of ViewConfiguration class after we make *PointerSlop*
//  functions public (see the comment at the top of the file).
//  After it will be a public API, we should get rid of `touchSlop / 144` and return absolute
//  value 0.125.dp.toPx(). It is not possible right now, because we can't access density.
private fun ViewConfiguration.pointerSlop(pointerType: PointerType): Float {
	return when (pointerType) {
		PointerType.Mouse -> touchSlop * mouseToTouchSlopRatio
		else -> touchSlop
	}
}