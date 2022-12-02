package io.github.hyuck9.hanimani.features.theme.ui

import android.os.Build
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.base.BaseViewModel
import io.github.hyuck9.hanimani.common.extension.select
import io.github.hyuck9.hanimani.common.theme.LightPrimary
import io.github.hyuck9.hanimani.common.theme.NightItemBackgroundL2
import io.github.hyuck9.hanimani.common.theme.NightPrimary
import io.github.hyuck9.hanimani.features.theme.data.IThemeEnvironment
import io.github.hyuck9.hanimani.model.Theme
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
	themeEnvironment: IThemeEnvironment
) : BaseViewModel<ThemeState, Unit, ThemeAction, IThemeEnvironment>(ThemeState(), themeEnvironment) {

	init {
		initTheme()
	}

	override fun dispatch(action: ThemeAction) {
		when (action) {
			is ThemeAction.SelectTheme -> applyTheme(action.selected)
		}
	}

	private fun initTheme() {
		viewModelScope.launch {
			setState { copy(items = initial()) }

			environment.getTheme()
				.collect {
					setState { copy(items = items.select(it)) }
				}
		}
	}

	private fun applyTheme(item: ThemeItem) {
		viewModelScope.launch {
			environment.setTheme(item.theme)
		}
	}

	private fun initial(): List<ThemeItem> {
		val data = mutableListOf<ThemeItem>()

		data.add(
			ThemeItem(
				R.string.setting_theme_automatic,
				Theme.SYSTEM,
				Brush.linearGradient(
					colors = listOf(Color.White, NightItemBackgroundL2)
				),
				false
			)
		)

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			data.add(
				ThemeItem(
					R.string.setting_theme_wallpaper,
					Theme.WALLPAPER,
					Brush.linearGradient(colors = listOf()),
					false
				)
			)
		}

		data.add(
			ThemeItem(
				R.string.setting_theme_light,
				Theme.LIGHT,
				Brush.linearGradient(
					colors = listOf(LightPrimary, Color.White)
				),
				false
			)
		)

		data.add(
			ThemeItem(
				R.string.setting_theme_night,
				Theme.NIGHT,
				Brush.linearGradient(
					colors = listOf(NightPrimary, NightItemBackgroundL2)
				),
				false
			)
		)

		return data
	}

}