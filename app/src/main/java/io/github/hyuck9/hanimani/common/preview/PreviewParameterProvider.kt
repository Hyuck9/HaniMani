package io.github.hyuck9.hanimani.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SampleBooleanProvider : PreviewParameterProvider<Boolean> {
	override val values: Sequence<Boolean> = sequenceOf(true, false)
}