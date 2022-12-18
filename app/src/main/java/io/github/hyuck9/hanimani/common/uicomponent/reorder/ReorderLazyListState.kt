package io.github.hyuck9.hanimani.common.uicomponent.reorder

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import io.github.hyuck9.hanimani.common.extension.getVisibleItemInfoFor
import io.github.hyuck9.hanimani.common.extension.offsetEnd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job


@Composable
fun rememberReorderLazyListState(
	lazyListState: LazyListState = rememberLazyListState(),
	coroutineScope: CoroutineScope = rememberCoroutineScope(),
	onMove: (Int, Int) -> Unit,
	onDragEnd: () -> Unit
): ReorderLazyListState {
	return remember { ReorderLazyListState(lazyListState, coroutineScope, onMove, onDragEnd) }
}

class ReorderLazyListState(
	val lazyListState: LazyListState,
	private val scope: CoroutineScope,
	private val onMove: (Int, Int) -> Unit,
	private val onDragEnd: () -> Unit
) {
	private var draggedDistance by mutableStateOf(0f)
	private var initiallyDraggedElement by mutableStateOf<LazyListItemInfo?>(null)
	private var currentIndexOfDraggedItem by mutableStateOf<Int?>(0)

	private val initialOffsets: Pair<Int, Int>?
		get() = initiallyDraggedElement?.let {
			Pair(it.offset, it.offsetEnd)
		}

	private val currentElement: LazyListItemInfo?
		get() = currentIndexOfDraggedItem?.let {
			lazyListState.getVisibleItemInfoFor(absoluteIndex = it)
		}

	val elementDisplacement: Float?
		get() = currentElement
			?.let { item ->
				(initiallyDraggedElement?.offset ?: 0f).toFloat() + draggedDistance - item.offset
			}

	private var overScrollJob by mutableStateOf<Job?>(null)

	fun onDragStart(offset: Offset) {
		lazyListState.layoutInfo.visibleItemsInfo
			.firstOrNull { item ->
				offset.y.toInt() in item.offset..(item.offset + item.size)
			}?.also {
				currentIndexOfDraggedItem = it.index
				initiallyDraggedElement = it
			}
	}

	fun onDragEnd() {
		onDragInterrupted()
		onDragEnd.invoke()
	}

	fun onDragInterrupted() {
		draggedDistance = 0f
		currentIndexOfDraggedItem = null
		initiallyDraggedElement = null
		overScrollJob?.cancel()
	}

	fun onDrag(offset: Offset) {
		draggedDistance += offset.y

		initialOffsets?.let { (topOffset, bottomOffset) ->
			val startOffset = topOffset + draggedDistance
			val endOffset = bottomOffset + draggedDistance

			currentElement?.let { hovered ->
				lazyListState.layoutInfo.visibleItemsInfo
					.filterNot { item ->
						item.offsetEnd < startOffset || item.offset > endOffset || hovered.index == item.index
					}
					.firstOrNull { item ->
						val delta = startOffset - hovered.offset
						when {
							delta > 0 -> (endOffset > item.offsetEnd)
							else -> (startOffset < item.offset)
						}
					}?.also { item ->
						currentIndexOfDraggedItem?.let { current ->
							onMove.invoke(current, item.index)
						}
						currentIndexOfDraggedItem = item.index
					}
			}
		}
	}

	fun checkForOverScroll(): Float = initiallyDraggedElement?.let { itemInfo ->
		val startOffset = itemInfo.offset + draggedDistance
		val endOffset = itemInfo.offsetEnd + draggedDistance

		when {
			draggedDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf { it > 0 }
			draggedDistance < 0 -> (startOffset - lazyListState.layoutInfo.viewportStartOffset).takeIf { it < 0 }
			else -> null
		}
	} ?: 0f
}