package io.github.hyuck9.hanimani.common.extension

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.hyuck9.hanimani.common.data.preference.model.FontSizePreference
import io.github.hyuck9.hanimani.features.settings.ui.FontSizeItem
import io.github.hyuck9.hanimani.model.FontSize

fun FontSize.toFontSizePreference() = when (this) {
	FontSize.SMALL -> FontSizePreference.FontSize.SMALL
	FontSize.MEDIUM -> FontSizePreference.FontSize.MEDIUM
	FontSize.LARGE -> FontSizePreference.FontSize.LARGE
}

fun FontSizePreference.FontSize.toFontSize() = when (this) {
	FontSizePreference.FontSize.UNRECOGNIZED,
	FontSizePreference.FontSize.SMALL -> FontSize.SMALL
	FontSizePreference.FontSize.MEDIUM -> FontSize.MEDIUM
	FontSizePreference.FontSize.LARGE -> FontSize.LARGE
}

@Composable
fun FontSize.toTextStyle() = when (this) {
	FontSize.SMALL -> MaterialTheme.typography.titleSmall
	FontSize.MEDIUM -> MaterialTheme.typography.titleMedium
	FontSize.LARGE -> MaterialTheme.typography.titleLarge
}

fun List<FontSizeItem>.select(fontSize: FontSize): List<FontSizeItem> {
	return map {
		it.copy(applied = it.fontSize == fontSize)
	}
}