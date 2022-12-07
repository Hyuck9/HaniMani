package io.github.hyuck9.hanimani.runtime

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

class ScreenOnReceiver : BroadcastReceiver() {

	override fun onReceive(context: Context, intent: Intent) {
		if (intent.action == Intent.ACTION_SCREEN_OFF) {
			if (Settings.canDrawOverlays(context)) {
				Intent(context, MainActivity::class.java).let {
					it.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
					it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
					context.startActivity(it)
				}

			}
		}

		if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
			context.startForegroundService(Intent(context, HaniManiService::class.java))
		}
	}

}