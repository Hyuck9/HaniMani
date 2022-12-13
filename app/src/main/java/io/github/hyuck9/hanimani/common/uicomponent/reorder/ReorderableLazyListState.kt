package io.github.hyuck9.hanimani.common.uicomponent.reorder

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberReorderableLazyListState(
	onMove: (ItemPosition, ItemPosition) -> Unit,
	listState: LazyListState = rememberLazyListState(),
	canDragOver: ((draggedOver: ItemPosition, dragging: ItemPosition) -> Boolean)? = null,
	onDragEnd: ((startIndex: Int, endIndex: Int) -> (Unit))? = null,
	dragCancelledAnimation: DragCancelledAnimation = SpringDragCancelledAnimation()
): ReorderableLazyListState {
	val scope = rememberCoroutineScope()
	val state = remember(listState) {
		ReorderableLazyListState(listState, scope, onMove, canDragOver, onDragEnd, dragCancelledAnimation)
	}
	val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
	LaunchedEffect(state) {
		state.visibleItemsChanged()
			.collect { state.onDrag(0, 0) }
	}

	LaunchedEffect(state) {
		var reverseDirection = !listState.layoutInfo.reverseLayout
		if (isRtl && listState.layoutInfo.orientation != Orientation.Vertical) {
			reverseDirection = !reverseDirection
		}
		val direction = if (reverseDirection) 1f else -1f
		while (true) {
			val diff = state.scrollChannel.receive()
			listState.scrollBy(diff * direction)
		}
	}
	return state
}

class ReorderableLazyListState(
	val listState: LazyListState,
	scope: CoroutineScope,
	onMove: (fromIndex: ItemPosition, toIndex: ItemPosition) -> (Unit),
	canDragOver: ((draggedOver: ItemPosition, dragging: ItemPosition) -> Boolean)? = null,
	onDragEnd: ((startIndex: Int, endIndex: Int) -> (Unit))? = null,
	dragCancelledAnimation: DragCancelledAnimation = SpringDragCancelledAnimation()
) : ReorderableState<LazyListItemInfo>(
	scope, onMove, canDragOver, onDragEnd, dragCancelledAnimation
) {
	override val LazyListItemInfo.left: Int
		get() = when {
			isVerticalScroll -> 0
			listState.layoutInfo.reverseLayout -> listState.layoutInfo.viewportSize.width - offset - size
			else -> offset
		}
	override val LazyListItemInfo.top: Int
		get() = when {
			!isVerticalScroll -> 0
			listState.layoutInfo.reverseLayout -> listState.layoutInfo.viewportSize.height - offset - size
			else -> offset
		}
	override val LazyListItemInfo.right: Int
		get() = when {
			isVerticalScroll -> 0
			listState.layoutInfo.reverseLayout -> listState.layoutInfo.viewportSize.width - offset
			else -> offset + size
		}
	override val LazyListItemInfo.bottom: Int
		get() = when {
			!isVerticalScroll -> 0
			listState.layoutInfo.reverseLayout -> listState.layoutInfo.viewportSize.height - offset
			else -> offset + size
		}


	override val LazyListItemInfo.width: Int
		get() = if (isVerticalScroll) 0 else size
	override val LazyListItemInfo.height: Int
		get() = if (isVerticalScroll) size else 0
	override val LazyListItemInfo.itemIndex: Int
		get() = index
	override val LazyListItemInfo.itemKey: Any
		get() = key


	override val visibleItemsInfo: List<LazyListItemInfo>
		get() = listState.layoutInfo.visibleItemsInfo

	override val firstVisibleItemIndex: Int
		get() = listState.firstVisibleItemIndex
	override val firstVisibleItemScrollOffset: Int
		get() = listState.firstVisibleItemScrollOffset

	override val viewportStartOffset: Int
		get() = listState.layoutInfo.viewportStartOffset
	override val viewportEndOffset: Int
		get() = listState.layoutInfo.viewportEndOffset
	override val isVerticalScroll: Boolean
		get() = listState.layoutInfo.orientation == Orientation.Vertical

	override suspend fun scrollToItem(index: Int, offset: Int) {
		listState.scrollToItem(index, offset)
	}

	override fun onDragStart(offsetX: Int, offsetY: Int): Boolean =
		if (isVerticalScroll) {
			super.onDragStart(0, offsetY)
		} else {
			super.onDragStart(offsetX, 0)
		}

	override fun findTargets(x: Int, y: Int, selected: LazyListItemInfo): List<LazyListItemInfo> =
		if (isVerticalScroll) {
			super.findTargets(0, y, selected)
		} else {
			super.findTargets(x, 0, selected)
		}

	override fun chooseDropItem(
		draggedItemInfo: LazyListItemInfo?,
		items: List<LazyListItemInfo>,
		curX: Int,
		curY: Int
	): LazyListItemInfo? =
		if (isVerticalScroll) {
			super.chooseDropItem(draggedItemInfo, items, 0, curY)
		} else {
			super.chooseDropItem(draggedItemInfo, items, curX, 0)
		}

}