package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme

@Composable
fun HmModalBackHeader(
	text: String,
	onClickBack: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier
				.padding(start = 16.dp)
				.weight(0.2F)
		) {
			HmModalBackButton(
				onClick = onClickBack
			)
		}

		HmModalTitle(
			text = text,
			modifier = Modifier.weight(0.6F)
		)

		Spacer(
			Modifier
				.size(0.dp)
				.weight(0.2F)
		)
	}
}

@Preview
@Composable
private fun HmModalBackHeaderPreview() {
	HaniManiTheme {
		HmModalBackHeader(
			text = "Themes",
			onClickBack = {}
		)
	}
}