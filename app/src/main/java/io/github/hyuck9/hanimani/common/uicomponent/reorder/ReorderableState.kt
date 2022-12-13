package io.github.hyuck9.hanimani.common.uicomponent.reorder

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.util.fastForEach
import io.github.hyuck9.hanimani.common.extension.StartDrag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

abstract class ReorderableState<T>(
	private val scope: CoroutineScope,
	private val onMove: (fromIndex: ItemPosition, toIndex: ItemPosition) -> (Unit),
	private val canDragOver: ((draggedOver: ItemPosition, dragging: ItemPosition) -> Boolean)?,
	private val onDragEnd: ((startIndex: Int, endIndex: Int) -> (Unit))?,
	val dragCancelledAnimation: DragCancelledAnimation
) {
	protected abstract val T.left: Int

	protected abstract val T.top: Int
	protected abstract val T.right: Int
	protected abstract val T.bottom: Int
	protected abstract val T.width: Int
	protected abstract val T.height: Int
	protected abstract val T.itemIndex: Int
	protected abstract val T.itemKey: Any
	protected abstract val visibleItemsInfo: List<T>

	protected abstract val firstVisibleItemIndex: Int
	protected abstract val firstVisibleItemScrollOffset: Int
	protected abstract val viewportStartOffset: Int
	protected abstract val viewportEndOffset: Int

	internal val interactions = Channel<StartDrag>()
	internal val scrollChannel = Channel<Float>()

	private var selected by mutableStateOf<T?>(null)
	var draggingItemIndex by mutableStateOf<Int?>(null)
	private var autoscroller: Job? = null
	private val targets = mutableListOf<T>()
	private val distances = mutableListOf<Int>()
	abstract val isVerticalScroll: Boolean

	val draggingItemKey: Any?
		get() = selected?.itemKey

	private val draggingLayoutInfo: T?
		get() = visibleItemsInfo
			.firstOrNull { it.itemIndex == draggingItemIndex }

	private var draggingDelta by mutableStateOf(Offset.Zero)

	val draggingItemLeft: Float
		get() = draggingLayoutInfo?.let { item ->
			(selected?.left ?: 0) + draggingDelta.x - item.left
		} ?: 0f
	val draggingItemTop: Float
		get() = draggingLayoutInfo?.let { item ->
			(selected?.top ?: 0) + draggingDelta.y - item.top
		} ?: 0f


	protected abstract suspend fun scrollToItem(index: Int, offset: Int)

	@OptIn(ExperimentalCoroutinesApi::class)
	internal fun visibleItemsChanged() =
		snapshotFlow { draggingItemIndex != null }
			.flatMapLatest { if (it) snapshotFlow { visibleItemsInfo } else flowOf(null) }
			.filterNotNull()
			.distinctUntilChanged { old, new -> old.firstOrNull()?.itemIndex == new.firstOrNull()?.itemIndex && old.count() == new.count() }

	internal open fun onDragStart(offsetX: Int, offsetY: Int): Boolean {
		val x: Int
		val y: Int
		if (isVerticalScroll) {
			x = offsetX
			y = offsetY + viewportStartOffset
		} else {
			x = offsetX + viewportStartOffset
			y = offsetY
		}
		return visibleItemsInfo
			.firstOrNull { x in it.left..it.right && y in it.top .. it.bottom }
			?.also {
				selected = it
				draggingItemIndex = it.itemIndex
			} != null
	}

	internal fun onDragCanceled() {
		val dragIdx = draggingItemIndex
		if (dragIdx != null) {
			val position = ItemPosition(dragIdx, selected?.itemKey)
			val offset = Offset(draggingItemLeft, draggingItemTop)
			scope.launch {
				dragCancelledAnimation.dragCancelled(position, offset)
			}
		}

		val startIndex = selected?.itemIndex
		val endIndex = draggingItemIndex
		selected = null
		draggingDelta = Offset.Zero
		draggingItemIndex = null
		cancelAutoScroll()
		onDragEnd?.apply {
			if (startIndex != null && endIndex != null) {
				invoke(startIndex, endIndex)
			}
		}
	}

	internal fun onDrag(offsetX: Int, offsetY: Int) {
		val selected = selected ?: return
		draggingDelta = Offset(draggingDelta.x + offsetX, draggingDelta.y + offsetY)
		val draggingItem = draggingLayoutInfo ?: return
		val startOffsetX = draggingItem.left + draggingItemLeft
		val startOffsetY = draggingItem.top + draggingItemTop
		chooseDropItem(
			draggingItem,
			findTargets(draggingDelta.x.toInt(), draggingDelta.y.toInt(), selected),
			startOffsetX.toInt(),
			startOffsetY.toInt()
		) ?.also { targetItem ->
			if (targetItem.itemIndex == firstVisibleItemIndex || draggingItem.itemIndex == firstVisibleItemIndex) {
				scope.launch {
					onMove.invoke(
						ItemPosition(
							draggingItem.itemIndex,
							draggingItem.itemKey
						),
						ItemPosition(
							targetItem.itemIndex,
							targetItem.itemKey
						)
					)
					scrollToItem(firstVisibleItemIndex, firstVisibleItemScrollOffset)
				}
			} else {
				onMove.invoke(
					ItemPosition(
						draggingItem.itemIndex,
						draggingItem.itemKey
					),
					ItemPosition(
						targetItem.itemIndex,
						targetItem.itemKey
					)
				)
			}
			draggingItemIndex = targetItem.itemIndex
		}

		with(calcAutoScrollOffset()) {
			if (this != 0f) autoscroll(this)
		}
	}


	private fun autoscroll(scrollOffset: Float) {
		if (scrollOffset != 0f) {
			if (autoscroller?.isActive == true) {
				return
			}
			autoscroller = scope.launch {
				var scroll = scrollOffset
				var start = 0L
				while (scroll != 0f && autoscroller?.isActive == true) {
					withFrameMillis {

						if (start == 0L) {
							start = it
						} else {
							scroll = calcAutoScrollOffset()
						}
					}
					scrollChannel.trySend(scroll)
				}
			}
		} else {
			cancelAutoScroll()
		}
	}

	private fun cancelAutoScroll() {
		autoscroller?.cancel()
		autoscroller = null
	}



	protected open fun findTargets(x: Int, y: Int, selected: T): List<T> {
		targets.clear()
		distances.clear()
		val left = x + selected.left
		val right = x + selected.right
		val top = y + selected.top
		val bottom = y + selected.bottom
		val centerX = (left + right) / 2
		val centerY = (top + bottom) / 2
		visibleItemsInfo.fastForEach { item ->
			if (
				item.itemIndex == draggingItemIndex
				|| item.bottom < top
				|| item.top > bottom
				|| item.right < left
				|| item.left > right
			) {
				return@fastForEach
			}
			if (canDragOver?.invoke(
					ItemPosition(item.itemIndex, item.itemKey),
					ItemPosition(selected.itemIndex, selected.itemKey)
				) != false
			) {
				val dx = (centerX - (item.left + item.right) / 2).absoluteValue
				val dy = (centerY - (item.top + item.bottom) / 2).absoluteValue
				val dist = dx * dx + dy * dy
				var pos = 0
				for (j in targets.indices) {
					if (dist > distances[j]) {
						pos++
					} else {
						break
					}
				}
				targets.add(pos, item)
				distances.add(pos, dist)
			}
		}
		return targets
	}

	protected open fun chooseDropItem(draggedItemInfo: T?, items: List<T>, curX: Int, curY: Int): T? {
		if (draggedItemInfo == null) {
			return if (draggingItemIndex != null) items.lastOrNull() else null
		}
		var target: T? = null
		var highScore = -1
		val right = curX + draggedItemInfo.width
		val bottom = curY + draggedItemInfo.height
		val dx = curX - draggedItemInfo.left
		val dy = curY - draggedItemInfo.top

		items.fastForEach { item ->
			if (dx > 0) {
				val diff = item.right - right
				if (diff < 0 && item.right > draggedItemInfo.right) {
					val score = diff.absoluteValue
					if (score > highScore) {
						highScore = score
						target = item
					}
				}
			}
			if (dx < 0) {
				val diff = item.left - curX
				if (diff > 0 && item.left < draggedItemInfo.left) {
					val score = diff.absoluteValue
					if (score > highScore) {
						highScore = score
						target = item
					}
				}
			}
			if (dy < 0) {
				val diff = item.top - curY
				if (diff > 0 && item.top < draggedItemInfo.top) {
					val score = diff.absoluteValue
					if (score > highScore) {
						highScore = score
						target = item
					}
				}
			}
			if (dy > 0) {
				val diff = item.bottom - bottom
				if (diff < 0 && item.bottom > draggedItemInfo.bottom) {
					val score = diff.absoluteValue
					if (score > highScore) {
						highScore = score
						target = item
					}
				}
			}
		}
		return target
	}

	private fun calcAutoScrollOffset(): Float {
		val draggingItem = draggingLayoutInfo ?: return 0f
		val startOffset: Float
		val endOffset: Float
		val delta: Float
		if (isVerticalScroll) {
			startOffset = draggingItem.top + draggingItemTop
			endOffset = startOffset + draggingItem.height
			delta = draggingDelta.y
		} else {
			startOffset = draggingItem.left + draggingItemLeft
			endOffset = startOffset + draggingItem.width
			delta = draggingDelta.x
		}
		return when {
			delta > 0 -> (endOffset - viewportEndOffset).takeIf { diff -> diff > 0 }
			delta < 0 -> (startOffset - viewportStartOffset).takeIf { diff -> diff < 0 }
			else -> null
		} ?: 0f
	}

}