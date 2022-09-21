package io.github.hyuck9.hanimani.extensions

import android.content.Intent
import android.os.Build
import java.io.Serializable

@Suppress("UNCHECKED_CAST")
fun <T : Serializable?> Intent.getSerializable(name: String, clazz: Class<T>): T {
	return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
		getSerializableExtra(name, clazz)!!
	} else {
		getSerializableExtra(name) as T
	}
}