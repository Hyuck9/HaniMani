package io.github.hyuck9.hanimani.runtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.hyuck9.hanimani.features.host.ui.HostScreen
import io.github.hyuck9.hanimani.runtime.navigation.HaniManiNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			HaniManiApp()
		}
	}
}

@Composable
fun HaniManiApp() {
	HostScreen {
		Surface {
			HaniManiNavGraph()
		}
	}
}


@Preview(showBackground = true)
@Composable
fun HaniManiAppPreview() {
	HaniManiApp()
}