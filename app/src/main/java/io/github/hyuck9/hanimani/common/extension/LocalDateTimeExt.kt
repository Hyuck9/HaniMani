package io.github.hyuck9.hanimani.common.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toMillis(): Long {
	val zoneId = ZoneId.systemDefault()
	return atZone(zoneId).toInstant().toEpochMilli()
}

fun Long.toLocalDateTime(): LocalDateTime {
	val zoneId = ZoneId.systemDefault()
	return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), zoneId)
}