package io.github.hyuck9.hanimani.common.data.local

import androidx.room.TypeConverter
import io.github.hyuck9.hanimani.common.extension.toLocalDateTime
import io.github.hyuck9.hanimani.common.extension.toMillis
import java.time.LocalDateTime

class DateConverter {

	@TypeConverter
	fun toDate(date: Long?): LocalDateTime? {
		if (date == null) return null

		return date.toLocalDateTime()
	}

	@TypeConverter
	fun toDateLong(date: LocalDateTime?): Long? {
		if (date == null) return null

		return date.toMillis()
	}

}