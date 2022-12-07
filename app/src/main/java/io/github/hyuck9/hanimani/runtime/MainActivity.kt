package io.github.hyuck9.hanimani.runtime

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.features.host.ui.HostScreen
import io.github.hyuck9.hanimani.runtime.navigation.HaniManiNavGraph
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			HaniManiApp()
		}
	}

	override fun onStart() {
		checkPermissions()
		super.onStart()
	}

	private fun checkPermissions() {
		if (Settings.canDrawOverlays(applicationContext).not()) {
			requestPermissionDialog()
		} else {
			startForegroundService(Intent(this, HaniManiService::class.java))
		}
	}

	private fun requestPermissionDialog() {
		AlertDialog.Builder(this)
			.setTitle(R.string.dialog_title)
			.setMessage(R.string.dialog_message)
			.setCancelable(false)
			.setPositiveButton(R.string.dialog_ok
			) { _, _ -> checkGoOverlay() }
			.create()
			.show()
	}

	private val startForResult = registerForActivityResult(
		ActivityResultContracts.StartActivityForResult()
	) { result ->
		Timber.i("startForResult - $result")
	}

	private fun checkGoOverlay() {
		val intent = Intent(
			Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
			Uri.parse("package:$packageName")
		)
		startForResult.launch(intent)
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
private fun HaniManiAppPreview() {
	HaniManiApp()
}