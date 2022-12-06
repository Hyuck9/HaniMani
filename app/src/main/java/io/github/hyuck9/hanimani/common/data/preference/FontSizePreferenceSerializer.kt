package io.github.hyuck9.hanimani.common.data.preference

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import io.github.hyuck9.hanimani.common.data.preference.model.FontSizePreference
import java.io.InputStream
import java.io.OutputStream

object FontSizePreferenceSerializer : Serializer<FontSizePreference> {

	override val defaultValue: FontSizePreference = FontSizePreference.getDefaultInstance()

	override suspend fun readFrom(input: InputStream): FontSizePreference {
		try {
			return FontSizePreference.parseFrom(input)
		} catch (exception: InvalidProtocolBufferException) {
			throw CorruptionException("Cannot read proto.", exception)
		}
	}

	override suspend fun writeTo(t: FontSizePreference, output: OutputStream) = t.writeTo(output)

}