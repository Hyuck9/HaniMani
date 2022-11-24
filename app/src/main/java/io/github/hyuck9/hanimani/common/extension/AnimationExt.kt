package io.github.hyuck9.hanimani.common.extension

fun lerp(
	startValue: Float,
	endValue: Float,
	fraction: Float
) = startValue + fraction * (endValue - startValue)