package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HmModalRow(
	modifier: Modifier = Modifier,
	horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
	verticalAlignment: Alignment.Vertical = Alignment.Top,
	content: @Composable RowScope.() -> Unit
) {
	Box(
		modifier = Modifier.background(
			color = MaterialTheme.colorScheme.background
		)
	) {
		Row(
			modifier = modifier
				.navigationBarsPadding()
				.imePadding(),
			horizontalArrangement = horizontalArrangement,
			verticalAlignment = verticalAlignment,
			content = content
		)
	}
}