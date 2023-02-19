package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun HmSnackbar(
	snackbarData: SnackbarData,
	modifier: Modifier = Modifier,
	actionOnNewLine: Boolean = false,
	shape: Shape = MaterialTheme.shapes.medium,
	containerColor: Color = SnackbarDefaults.color,
	contentColor: Color = SnackbarDefaults.contentColor,
	actionColor: Color = MaterialTheme.colorScheme.primary,
	actionContentColor: Color = MaterialTheme.colorScheme.onPrimary,
)  {
	Snackbar(
		snackbarData = snackbarData,
		modifier = modifier,
		actionOnNewLine = actionOnNewLine,
		shape = shape,
		containerColor = containerColor,
		contentColor = contentColor,
		actionColor = actionColor,
		actionContentColor = actionContentColor,
	)
}