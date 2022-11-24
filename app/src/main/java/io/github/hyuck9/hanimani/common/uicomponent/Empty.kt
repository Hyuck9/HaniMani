package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.theme.AlphaDisabled

@Composable
fun EmptyTaskTipText(
	modifier: Modifier = Modifier
) {
	Box(modifier = modifier.fillMaxWidth()) {
		Column(
			modifier = Modifier.align(Alignment.Center),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = stringResource(id = R.string.message_default_tip_title),
				color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaDisabled)
			)
			Spacer(modifier = Modifier.height(24.dp))
			Text(
				text = stringResource(id = R.string.message_default_tip_content),
				color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaDisabled)
			)
		}
	}
}

@Preview(showBackground = true, heightDp = 100)
@Composable
fun EmptyTaskTipTextPreview() {
	EmptyTaskTipText()
}