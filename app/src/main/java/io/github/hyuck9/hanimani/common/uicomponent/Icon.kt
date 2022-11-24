package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.github.hyuck9.hanimani.common.preview.SampleBooleanProvider
import io.github.hyuck9.hanimani.common.theme.AlphaDisabled

@Composable
fun HmIcon(
	modifier: Modifier = Modifier,
	imageVector: ImageVector,
	tint: Color = LocalContentColor.current
) {
	Icon(
		imageVector = imageVector,
		contentDescription = "",
		tint = tint,
		modifier = modifier
	)
}


@Preview(showBackground = true)
@Composable
private fun HmIconPreview(@PreviewParameter(SampleBooleanProvider::class) isValid: Boolean) {
	HmIcon(
		imageVector = Icons.Rounded.ArrowUpward,
		tint = if (isValid) {
			LocalContentColor.current
		} else {
			LocalContentColor.current.copy(alpha = AlphaDisabled)
		}
	)
}