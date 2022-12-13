package io.github.hyuck9.hanimani.common.uicomponent.reorder

import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

interface DragCancelledAnimation {
	suspend fun dragCancelled(position: ItemPosition, offset: Offset)
	val position: ItemPosition?
	val offset: Offset
}

class NoDragCancelledAnimation : DragCancelledAnimation {
	override suspend fun dragCancelled(position: ItemPosition, offset: Offset) {}
	override val position: ItemPosition? = null
	override val offset: Offset = Offset.Zero
}

class SpringDragCancelledAnimation(
	private val stiffness: Float = Spring.StiffnessMediumLow
) : DragCancelledAnimation {
	private val animatable = Animatable(Offset.Zero, Offset.VectorConverter)
	override val offset: Offset
		get() = animatable.value

	override var position by mutableStateOf<ItemPosition?>(null)
		private set

	override suspend fun dragCancelled(position: ItemPosition, offset: Offset) {
		this.position = position
		animatable.snapTo(offset)
		animatable.animateTo(
			Offset.Zero,
			spring(stiffness = stiffness, visibilityThreshold = Offset.VisibilityThreshold)
		)
		this.position = null
	}
}