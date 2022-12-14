package io.github.hyuck9.hanimani.common.extension

import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import io.github.hyuck9.hanimani.features.theme.ui.ThemeItem
import io.github.hyuck9.hanimani.model.Theme

fun Theme.toThemePreference() = when (this) {
	Theme.SYSTEM -> ThemePreference.ThemeColor.SYSTEM
	Theme.WALLPAPER -> ThemePreference.ThemeColor.WALLPAPER
	Theme.LIGHT -> ThemePreference.ThemeColor.LIGHT
	Theme.TWILIGHT -> ThemePreference.ThemeColor.TWILIGHT
	Theme.NIGHT -> ThemePreference.ThemeColor.NIGHT
	Theme.SUNRISE -> ThemePreference.ThemeColor.SUNRISE
	Theme.AURORA -> ThemePreference.ThemeColor.AURORA
	Theme.PINK -> ThemePreference.ThemeColor.PINK
	Theme.PURPLE -> ThemePreference.ThemeColor.PURPLE
	Theme.BLUE -> ThemePreference.ThemeColor.BLUE
}

fun ThemePreference.ThemeColor.toTheme() = when (this) {
	ThemePreference.ThemeColor.UNRECOGNIZED,
	ThemePreference.ThemeColor.SYSTEM -> Theme.SYSTEM
	ThemePreference.ThemeColor.WALLPAPER -> Theme.WALLPAPER
	ThemePreference.ThemeColor.LIGHT -> Theme.LIGHT
	ThemePreference.ThemeColor.TWILIGHT -> Theme.TWILIGHT
	ThemePreference.ThemeColor.NIGHT -> Theme.NIGHT
	ThemePreference.ThemeColor.SUNRISE -> Theme.SUNRISE
	ThemePreference.ThemeColor.AURORA -> Theme.AURORA
	ThemePreference.ThemeColor.PINK -> Theme.PINK
	ThemePreference.ThemeColor.PURPLE -> Theme.PURPLE
	ThemePreference.ThemeColor.BLUE -> Theme.BLUE
}

fun List<ThemeItem>.select(theme: Theme): List<ThemeItem> {
	return map {
		it.copy(applied = it.theme == theme)
	}
}