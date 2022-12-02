package io.github.hyuck9.hanimani.common.data.preference

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import java.io.InputStream
import java.io.OutputStream

object ThemePreferenceSerializer : Serializer<ThemePreference> {

	override val defaultValue: ThemePreference = ThemePreference.getDefaultInstance()

	override suspend fun readFrom(input: InputStream): ThemePreference {
		try {
			return ThemePreference.parseFrom(input)
		} catch (exception: InvalidProtocolBufferException) {
			throw CorruptionException("Cannot read proto.", exception)
		}
	}

	override suspend fun writeTo(t: ThemePreference, output: OutputStream) = t.writeTo(output)

}