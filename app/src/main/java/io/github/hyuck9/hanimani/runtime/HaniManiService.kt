package io.github.hyuck9.hanimani.runtime

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import androidx.core.app.NotificationCompat
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.runtime.ServiceActions.START_FOREGROUND
import io.github.hyuck9.hanimani.runtime.ServiceActions.STOP_FOREGROUND

class HaniManiService : Service() {

	private var screenOnReceiver: ScreenOnReceiver? = null

	override fun onBind(intent: Intent): IBinder? { return null }

	override fun onCreate() {
		setScreenOnReceiver()
		super.onCreate()
	}

	override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

		when (intent.action) {
			START_FOREGROUND -> startForegroundService()
			STOP_FOREGROUND -> stopForegroundService()
		}

		return START_STICKY
	}

	private fun startForegroundService() {
		val channel = NotificationChannel(CHANNEL_ID, "HaniMani Service Channel", NotificationManager.IMPORTANCE_LOW)
		channel.setShowBadge(false)

		val notiManager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
		notiManager.createNotificationChannel(channel)

		val pendingIntent = PendingIntent.getActivity(this, 0, Intent(applicationContext, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE)
		val builder = NotificationCompat.Builder(this, CHANNEL_ID)
			.setSmallIcon(R.drawable.ic_task_alt)
			.setContentIntent(pendingIntent)
			.setContentTitle(resources.getString(R.string.noti_content_title))
			.setContentText(resources.getString(R.string.noti_content_text))

		notiManager.notify(CHANNEL_ID.hashCode(), builder.build())
		startForeground(CHANNEL_ID.hashCode(), builder.build())
	}

	private fun stopForegroundService() {
		stopForeground(STOP_FOREGROUND_REMOVE)
		stopSelf()
	}

	private fun setScreenOnReceiver() {
		val intentFilter = IntentFilter().apply {
			addAction(Intent.ACTION_SCREEN_ON)
			addAction(Intent.ACTION_SCREEN_OFF)
		}
		screenOnReceiver = ScreenOnReceiver()
		registerReceiver(screenOnReceiver, intentFilter)
	}

	override fun onDestroy() {
		screenOnReceiver?.let {
			unregisterReceiver(screenOnReceiver)
		}
		super.onDestroy()
	}

	companion object {
		const val CHANNEL_ID = "hanimani_service_channel"
	}

}