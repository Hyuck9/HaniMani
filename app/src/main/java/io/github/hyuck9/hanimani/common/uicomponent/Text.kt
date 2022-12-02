package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HmModalTitle(
	text: String,
	modifier: Modifier = Modifier,
	textColor: Color = Color.Unspecified
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = modifier.fillMaxWidth()
	) {
		Text(
			text = text,
			style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
			color = textColor
		)
	}
}