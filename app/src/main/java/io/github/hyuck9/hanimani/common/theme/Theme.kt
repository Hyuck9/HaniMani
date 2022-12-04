package io.github.hyuck9.hanimani.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.hyuck9.hanimani.model.Theme

//private val DarkColorScheme = darkColorScheme(
//	primary = Purple80,
//	secondary = PurpleGrey80,
//	tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//	primary = Purple40,
//	secondary = PurpleGrey40,
//	tertiary = Pink40
//
//	/* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)


/*******************************************************************************
 * ColorScheme 속성값
 *******************************************************************************
 * [primary] - 화면의 구성요소에서 가장 자주 표시되는 기본 색상
 * [primaryContainer] - 기본 컨테이너 색상
 * [secondary] - 강조 및 구별하는데 많이 쓰이는 색상
 * 		Secondary colors are best for:
 * 			- Floating action buttons
 * 			- Selection controls, like checkboxes and radio buttons
 * 			- Highlighting selected text
 * 			- Links and headlines
 * [secondaryContainer] - A tonal color to be used in containers.
 * [background] - 스크롤 가능한 컨텐츠의 배경 색상
 * [surface] - 카드, 시트 및 메뉴와 같은 컴포넌트의 표면 색상
 * [surfaceVariant] - [surface]와 비슷한 용도
 * [error] - TextField의 잘못된 텍스트와 같은 컴포넌트의 오류를 나타내는데 사용되는 색상
 * [onPrimary] - primary color 위에 표시되는 텍스트 및 아이콘에 사용되는 색상
 * [onPrimaryContainer] - [primaryContainer] 위에 있는 컨텐츠에 사용하는 색상
 * [onSecondary] - secondary color 위에 표시되는 텍스트 및 아이콘에 사용되는 색상
 * [onSecondaryContainer] - [secondaryContainer] 위에 있는 컨텐츠에 사용하는 색상
 * [onBackground] - background color 위에 표시되는 텍스트 및 아이콘에 사용되는 색상
 * [onSurface] surface color 위에 표시되는 텍스트 및 아이콘에 사용되는 색상
 * [onSurfaceVariant] - [surface] 위에 있는 컨텐츠에 사용하는 색상
 * [onError] error color 위에 표시되는 텍스트 및 아이콘에 사용되는 색상
 *******************************************************************************/

val LightColorPalette = lightColorScheme(
	primary = LightPrimary,
	primaryContainer = LightPrimary,
	secondary = LightItemBackgroundL1,
	secondaryContainer = LightItemBackgroundL1,
	background = LightBackgroundL2,
	surface = LightBackgroundL1,
	surfaceVariant = LightItemBackgroundL2,
	error = LightError,
	onPrimary = Color.White,
	onPrimaryContainer = Color.White,
	onSecondary = LightOn,
	onSecondaryContainer = LightOn,
	onBackground = LightOn,
	onSurface = LightOn,
	onSurfaceVariant = LightOn,
	onError = Color.White
)

val NightColorPalette = darkColorScheme(
	primary = NightPrimary,
	primaryContainer = NightPrimary,
	secondary = NightItemBackgroundL1,
	secondaryContainer = NightItemBackgroundL1,
	background = NightBackgroundL2,
	surface = NightBackgroundL1,
	surfaceVariant = NightItemBackgroundL2,
	error = Error,
	onPrimary = NightOn,
	onPrimaryContainer = NightOn,
	onSecondary = NightOn,
	onSecondaryContainer = NightOn,
	onBackground = NightOn,
	onSurface = NightOn,
	onSurfaceVariant = NightOn,
	onError = Color.White
)






@Composable
fun HaniManiTheme(
	isDarkTheme: Boolean = isSystemInDarkTheme(),
	theme: Theme = Theme.SYSTEM,
	content: @Composable () -> Unit
) {
	val context = LocalContext.current
	val colors = when (theme) {
		Theme.SYSTEM -> {
			if (isDarkTheme) {
				NightColorPalette
			} else {
				LightColorPalette
			}
		}
		Theme.WALLPAPER -> {
			if (isDarkTheme) {
				dynamicDarkColorScheme(context)
			} else {
				dynamicLightColorScheme(context)
			}
		}
		Theme.LIGHT -> LightColorPalette
		Theme.NIGHT -> NightColorPalette
	}
	val darkIcons = colors == LightColorPalette || (theme == Theme.WALLPAPER && isDarkTheme.not())
	val systemUiController = rememberSystemUiController()

	SideEffect {
		systemUiController.setSystemBarsColor(
			color = Color.Transparent,
			darkIcons = darkIcons,
			isNavigationBarContrastEnforced = false
		)
	}

	MaterialTheme(
		colorScheme = colors,
		typography = Typography,
		shapes = Shapes,
		content = content
	)

}