package io.github.hyuck9.hanimani.common.data.preference

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import io.github.hyuck9.hanimani.common.data.preference.model.TextAlignPreference
import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import java.io.InputStream
import java.io.OutputStream

object TextAlignPreferenceSerializer : Serializer<TextAlignPreference> {

	override val defaultValue: TextAlignPreference = TextAlignPreference.getDefaultInstance()

	override suspend fun readFrom(input: InputStream): TextAlignPreference {
		try {
			return TextAlignPreference.parseFrom(input)
		} catch (exception: InvalidProtocolBufferException) {
			throw CorruptionException("Cannot read proto.", exception)
		}
	}

	override suspend fun writeTo(t: TextAlignPreference, output: OutputStream) = t.writeTo(output)

}