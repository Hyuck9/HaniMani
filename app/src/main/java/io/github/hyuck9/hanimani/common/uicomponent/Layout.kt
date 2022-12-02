package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HmModalLayout(
	title: @Composable () -> Unit,
	modifier: Modifier = Modifier,
	content: LazyListScope.() -> Unit
) {
	HmModalLazyColumn(modifier) {
		item {
			Spacer(Modifier.height(24.dp))
			title()
			Spacer(Modifier.height(24.dp))
		}

		content()

		item {
			Spacer(Modifier.height(8.dp))
		}
	}
}

@Composable
fun HmModalLazyColumn(
	modifier: Modifier = Modifier,
	shape: Shape = RectangleShape,
	content: LazyListScope.() -> Unit
) {
	Box(
		modifier = Modifier.background(
			color = MaterialTheme.colorScheme.background,
			shape = shape
		)
	) {
		LazyColumn(
			modifier = modifier
				.navigationBarsPadding()
				.imePadding(),
			content = content
		)
	}
}

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