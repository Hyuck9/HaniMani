@file:Suppress("KDocUnresolvedReference")

package io.github.hyuck9.hanimani.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.hyuck9.hanimani.model.Theme

/* *****************************************************************************
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

private val LightColorPalette = lightColorScheme(
	primary = LightPrimary,						// 탭 text/icon 및 테두리 			| Task 체크박스 							| Task 우측Swipe 배경색 (완료) 	| 완료 헤더 등
	primaryContainer = LightPrimary,			// fab 버튼색 						| Task 추가 버튼 색						|								|
	secondary = LightItemBackgroundL1,			// Task Swipe 기본 배경색 			| Task 추가 text 입력창 배경색				|								|
	background = LightBackgroundL2,				// 기본 앱 배경색 					| 할일 목록 배경색 						| BottomSheet 배경색				|
	surface = LightBackgroundL1,				// 탭 표면색 						| Task item 표면색 						| setting item 표면색			|
	surfaceVariant = LightItemBackgroundL2,		// Task 추가 버튼 색 (비활성) 			| Themes Item 표면색 (미체크) 			| BottomSheet Back 버튼 표면색	|
	error = LightError,							// Task 좌측Swipe 배경색 (삭제) 		| Complete Header Delete Button 글자색	|								|
	onPrimary = Color.White,					// fab text/icon 색 				| Themes Item 글자색 (체크)				|								|
	onBackground = LightOn,						// Task 글자색 						| Setting subtitle 글자색 				| setting item 글자색 			| Themes BottomSheet title 글자색
	onSurface = LightOn,						// Task 추가 글자색 					| 도움말 글자색 							| Task item 구분선 				| Complete Header Delete Button 클릭 이펙트
	onSurfaceVariant = LightOn,					// Task 추가 submit 버튼 아이콘 tint 	| Themes Item 글자색 (미체크)				|								|
)

val TwilightColorPalette = lightColorScheme(
	primary = TwilightPrimary,
	primaryContainer = TwilightPrimary,
	secondary = TwilightItemBackgroundL1,
	background = TwilightBackgroundL2,
	surface = TwilightBackgroundL1,
	surfaceVariant = TwilightItemBackgroundL2,
	error = CommonError,
	onPrimary = TwilightOn,
	onBackground = TwilightOn,
	onSurface = TwilightOn,
	onSurfaceVariant = TwilightOn,
)

private val NightColorPalette = darkColorScheme(
	primary = NightPrimary,
	primaryContainer = NightPrimary,
	secondary = NightItemBackgroundL1,
	background = NightBackgroundL2,
	surface = NightBackgroundL1,
	surfaceVariant = NightItemBackgroundL2,
	error = CommonError,
	onPrimary = NightOn,
	onBackground = NightOn,
	onSurface = NightOn,
	onSurfaceVariant = NightOn,
)

val SunriseColorPalette = darkColorScheme(
	primary = SunrisePrimary,
	primaryContainer = SunrisePrimary,
	secondary = SunriseItemBackgroundL1,
	background = SunriseBackgroundL2,
	surface = SunriseBackgroundL1,
	surfaceVariant = SunriseItemBackgroundL2,
	error = SunriseError,
	onPrimary = SunriseOn,
	onBackground = SunriseOn,
	onSurface = SunriseOn,
	onSurfaceVariant = SunriseOn,
)

val AuroraColorPalette = darkColorScheme(
	primary = AuroraPrimary,
	primaryContainer = AuroraPrimary,
	secondary = AuroraItemBackgroundL1,
	background = AuroraBackgroundL2,
	surface = AuroraBackgroundL1,
	surfaceVariant = AuroraItemBackgroundL2,
	error = CommonError,
	onPrimary = AuroraOn,
	onBackground = AuroraOn,
	onSurface = AuroraOn,
	onSurfaceVariant = AuroraOn,
)

val PinkColorPalette = lightColorScheme(
	primary = PinkPrimary,
	primaryContainer = PinkPrimary,
	secondary = PinkItemBackgroundL1,
	background = PinkBackgroundL2,
	surface = PinkBackgroundL1,
	surfaceVariant = PinkItemBackgroundL2,
	error = PinkError,
	onPrimary = Color.White,
	onBackground = PinkOn,
	onSurface = PinkOn,
	onSurfaceVariant = PinkOn,
)

val PurpleColorPalette = lightColorScheme(
	primary = PurplePrimary,
	primaryContainer = PurplePrimary,
	secondary = PurpleItemBackgroundL1,
	background = PurpleBackgroundL2,
	surface = PurpleBackgroundL1,
	surfaceVariant = PurpleItemBackgroundL2,
	error = PurpleError,
	onPrimary = Color.White,
	onBackground = PurpleOn,
	onSurface = PurpleOn,
	onSurfaceVariant = PurpleOn,
)

val BlueColorPalette = lightColorScheme(
	primary = BluePrimary,
	primaryContainer = BluePrimaryContainer,
	secondary = BlueItemBackgroundL1,
	background = BlueBackgroundL2,
	surface = BlueBackgroundL1,
	surfaceVariant = BlueItemBackgroundL2,
	error = BlueError,
	onPrimary = Color.White,
	onPrimaryContainer = Color.White,
	onBackground = Color.White,
	onSurface = BlueOn,
	onSurfaceVariant = BlueOn,
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
		Theme.TWILIGHT -> TwilightColorPalette
		Theme.NIGHT -> NightColorPalette
		Theme.SUNRISE -> SunriseColorPalette
		Theme.AURORA -> AuroraColorPalette
		Theme.PINK -> PinkColorPalette
		Theme.PURPLE -> PurpleColorPalette
		Theme.BLUE -> BlueColorPalette
	}
	val darkIcons = colors == LightColorPalette || (theme == Theme.WALLPAPER && isDarkTheme.not()) || colors == SunriseColorPalette
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