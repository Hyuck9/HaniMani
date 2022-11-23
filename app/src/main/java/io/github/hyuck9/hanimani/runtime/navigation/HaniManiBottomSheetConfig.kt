package io.github.hyuck9.hanimani.runtime.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import io.github.hyuck9.hanimani.common.theme.LargeRadius
import io.github.hyuck9.hanimani.common.theme.SmallRadius

@Immutable
data class HaniManiBottomSheetConfig(
	val sheetShape: Shape,
	val showScrim: Boolean
)

val DefaultMainBottomSheetConfig = HaniManiBottomSheetConfig(
	RoundedCornerShape(
		topStart = LargeRadius,
		topEnd = LargeRadius
	),
	true
)
val NoScrimSmallShapeMainBottomSheetConfig = HaniManiBottomSheetConfig(
	RoundedCornerShape(
		topStart = SmallRadius,
		topEnd = SmallRadius
	),
	false
)