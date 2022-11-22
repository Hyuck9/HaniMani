package io.github.hyuck9.hanimani.features.common

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.common.theme.Purple40

@Composable
fun HaniManiTabBar(
	tabPage: TabPage,
	onTabSelected: (TabPage) -> Unit
) {
	TabRow(
		selectedTabIndex = tabPage.ordinal,
		indicator = { tabPositions ->
			HaniManiTabIndicator(tabPositions, tabPage)
		}
	) {
		HaniManiTab(
			icon = Icons.Default.TaskAlt,
			title = stringResource(id = R.string.tab_title_tasks),
			onClick = { onTabSelected(TabPage.Tasks) }
		)
		HaniManiTab(
			icon = Icons.Default.Settings,
			title = stringResource(id = R.string.tab_title_settings),
			onClick = { onTabSelected(TabPage.Settings) }
		)
	}
}

@Composable
private fun HaniManiTab(
	icon: ImageVector,
	title: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.clickable(onClick = onClick)
			.padding(16.dp),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			imageVector = icon,
			contentDescription = title
		)
		Spacer(modifier = Modifier.width(16.dp))
		Text(text = title)
	}
}

@Composable
private fun HaniManiTabIndicator(
	tabPositions: List<TabPosition>,
	tabPage: TabPage
) {
	val transition = updateTransition(tabPage, "Tab indicator")
	val indicatorLeft by transition.animateDp(
		transitionSpec = {
			if (TabPage.Tasks isTransitioningTo TabPage.Settings) {
				spring(stiffness = Spring.StiffnessVeryLow)
			} else {
				spring(stiffness = Spring.StiffnessMedium)
			}
		},
		label = "Indicator left"
	) { page ->
		tabPositions[page.ordinal].left
	}
	val indicatorRight by transition.animateDp(
		transitionSpec = {
			if (TabPage.Tasks isTransitioningTo TabPage.Settings) {
				spring(stiffness = Spring.StiffnessMedium)
			} else {
				spring(stiffness = Spring.StiffnessVeryLow)
			}
		},
		label = "Indicator right"
	) { page ->
		tabPositions[page.ordinal].right
	}
	Box(
		Modifier
			.fillMaxSize()
			.wrapContentSize(align = Alignment.BottomStart)
			.offset(x = indicatorLeft)
			.width(indicatorRight - indicatorLeft)
			.padding(4.dp)
			.fillMaxSize()
			.border(
				BorderStroke(2.dp, Purple40),
				RoundedCornerShape(4.dp)
			)
	)
}



@Preview(showBackground = true)
@Composable
fun HaniManiTabPreview() {
	HaniManiTheme {
		HaniManiTab(
			icon = Icons.Default.TaskAlt,
			title = stringResource(id = R.string.tab_title_tasks),
			onClick = {}
		)
	}
}


@Preview(showBackground = true)
@Composable
fun TabBarPreview() {
	HaniManiTheme {
		HaniManiTabBar(
			tabPage = TabPage.Tasks,
			onTabSelected = {}
		)
	}
}
