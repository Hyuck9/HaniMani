package io.github.hyuck9.hanimani

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
			Timber.d("Timber is initialized.")
		}
	}
}