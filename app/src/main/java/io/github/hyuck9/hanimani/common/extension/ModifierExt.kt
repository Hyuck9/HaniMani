package io.github.hyuck9.hanimani.common.extension

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent

fun Modifier.drawGrowingCircle(
	color: Color,
	center: Offset,
	radius: Float
) = drawWithContent {
	drawContent()
	clipRect {
		drawCircle(
			color = color,
			radius = radius,
			center = center
		)
	}
}

fun Modifier.onPositionInParentChanged(
	onChange: (LayoutCoordinates) -> Unit
) = composed {
	var lastPosition by remember { mutableStateOf(Offset.Zero) }
	Modifier.onGloballyPositioned { coordinates ->
		if (coordinates.positionInParent() != lastPosition) {
			lastPosition = coordinates.positionInParent()
			onChange(coordinates)
		}
	}
}