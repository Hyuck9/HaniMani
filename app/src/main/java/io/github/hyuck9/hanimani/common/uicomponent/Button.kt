package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HmModalBackButton(
	onClick: () -> Unit,
	imageVector: ImageVector = Icons.Rounded.KeyboardArrowLeft
) {
	HmIconButton(
		onClick = onClick,
		modifier = Modifier.size(28.dp),
		color = MaterialTheme.colorScheme.surfaceVariant
	) {
		HmIcon(
			imageVector = imageVector,
		)
	}
}

@Composable
fun HmIconButton(
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	color: Color = MaterialTheme.colorScheme.secondary,
	content: @Composable () -> Unit
) {
	val shape = CircleShape
	IconButton(
		onClick = onClick,
		modifier = modifier.background(
			color = color,
			shape = shape
		).clip(shape),
		enabled = enabled
	) {
		content()
	}
}