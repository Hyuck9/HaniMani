package io.github.hyuck9.hanimani.runtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme
import io.github.hyuck9.hanimani.runtime.navigation.HaniManiNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			HaniManiApp()
		}
	}
}

@Composable
fun HaniManiApp() {
	HaniManiTheme {
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