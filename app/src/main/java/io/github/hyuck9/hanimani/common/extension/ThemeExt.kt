package io.github.hyuck9.hanimani.common.extension

import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import io.github.hyuck9.hanimani.features.theme.ui.ThemeItem
import io.github.hyuck9.hanimani.model.Theme

fun Theme.toThemePreference() = when (this) {
	Theme.SYSTEM -> ThemePreference.ThemeColor.SYSTEM
	Theme.WALLPAPER -> ThemePreference.ThemeColor.WALLPAPER
	Theme.LIGHT -> ThemePreference.ThemeColor.LIGHT
	Theme.NIGHT -> ThemePreference.ThemeColor.NIGHT
}

fun ThemePreference.ThemeColor.toTheme() = when (this) {
	ThemePreference.ThemeColor.UNRECOGNIZED,
	ThemePreference.ThemeColor.SYSTEM -> Theme.SYSTEM
	ThemePreference.ThemeColor.WALLPAPER -> Theme.WALLPAPER
	ThemePreference.ThemeColor.LIGHT -> Theme.LIGHT
	ThemePreference.ThemeColor.NIGHT -> Theme.NIGHT
}

fun List<ThemeItem>.select(theme: Theme): List<ThemeItem> {
	return map {
		it.copy(applied = it.theme == theme)
	}
}