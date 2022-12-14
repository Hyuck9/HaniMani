package io.github.hyuck9.hanimani.common.uiextension

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@ExperimentalMaterialNavigationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberBottomSheetNavigator(
	animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec
): BottomSheetNavigator {
	val sheetState = rememberModalBottomSheetState(
		ModalBottomSheetValue.Hidden,
		animationSpec
	)
	return remember(sheetState) {
		BottomSheetNavigator(sheetState = sheetState)
	}
}

@Composable
@ExperimentalMaterialApi
private fun rememberModalBottomSheetState(
	initialValue: ModalBottomSheetValue,
	animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
	confirmStateChange: (ModalBottomSheetValue) -> Boolean = { true }
): ModalBottomSheetState = rememberModalBottomSheetState(
	initialValue = initialValue,
	animationSpec = animationSpec,
	skipHalfExpanded = true,
	confirmStateChange = confirmStateChange
)
