package io.github.hyuck9.hanimani.common.extension

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import io.github.hyuck9.hanimani.common.uicomponent.reorder.ReorderLazyListState

fun LazyListState.getVisibleItemInfoFor(absoluteIndex: Int): LazyListItemInfo? {
	return if (layoutInfo.visibleItemsInfo.isNotEmpty()) {
		layoutInfo.visibleItemsInfo.getOrNull(absoluteIndex - layoutInfo.visibleItemsInfo.first().index)
	} else {
		null
	}
}

val LazyListItemInfo.offsetEnd: Int
	get() = this.offset + this.size

fun Modifier.reorderble(
	state: ReorderLazyListState,

) = then(Modifier.pointerInput(Unit) {
		detectDragGesturesAfterLongPress(
			onDrag = { change, offset ->
				change.consume()
				state.onDrag(offset = offset)
			},
			onDragStart = { offset -> state.onDragStart(offset) },
			onDragEnd = { state.onDragEnd() },
			onDragCancel = { state.onDragInterrupted() }
		)
	}
)