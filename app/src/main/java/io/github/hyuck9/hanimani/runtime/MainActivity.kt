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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.data.preference.PreferencesConstants.AUTO_RUN
import io.github.hyuck9.hanimani.common.data.preference.readBoolean
import io.github.hyuck9.hanimani.common.data.preference.writeBoolean
import io.github.hyuck9.hanimani.features.host.ui.HostScreen
import io.github.hyuck9.hanimani.runtime.ServiceActions.START_FOREGROUND
import io.github.hyuck9.hanimani.runtime.ServiceActions.STOP_FOREGROUND
import io.github.hyuck9.hanimani.runtime.navigation.HaniManiNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			HaniManiApp()

			val collectAsState = readBoolean(AUTO_RUN, true).collectAsState(initial = true)
			Timber.i("collectAsState : ${collectAsState.value}")
			checkPermissions(collectAsState.value)
		}

	}


	private fun checkPermissions(isAutorun: Boolean) {
		if (isAutorun) {
			if (Settings.canDrawOverlays(applicationContext).not()) {
				requestPermissionDialog()
			} else {
				startService(Intent(this, HaniManiService::class.java).apply { action = START_FOREGROUND })
			}
		} else {
			startService(Intent(this, HaniManiService::class.java).apply { action = STOP_FOREGROUND })
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
	) {
		// 앱 위에 그리기 권한 설정에 대한 응답 중
		if (Settings.canDrawOverlays(applicationContext)) {
			// 앱 위에 그리기 허용한 경우 포그라운드 서비스 실행
			startService(Intent(this, HaniManiService::class.java).apply { action = START_FOREGROUND })
		} else {
			// 앱 위에 그리기 허용 안한 경우 서비스 내리고, AUTU_RUN 설정 false로 변경
			startService(Intent(this, HaniManiService::class.java).apply { action = STOP_FOREGROUND })
			CoroutineScope(Dispatchers.IO).launch { writeBoolean(AUTO_RUN, false) }
		}
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