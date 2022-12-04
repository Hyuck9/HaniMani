package io.github.hyuck9.hanimani.model

import java.time.LocalDateTime
import java.util.*

data class ToDoTask(
	val id: String = UUID.randomUUID().toString(),
	val name: String = "",
	val status: ToDoStatus = ToDoStatus.IN_PROGRESS,
	val order: Int = 0,
	val completedAt: LocalDateTime? = null,
	val createdAt: LocalDateTime = LocalDateTime.now(),
	val updatedAt: LocalDateTime = LocalDateTime.now(),
) {


	suspend fun toggleStatusHandler(
		currentDate: LocalDateTime,
		onUpdateStatus: suspend (completedAt: LocalDateTime?, newStatus: ToDoStatus) -> Unit
	) {
		val newStatus = status.toggle()
		val completedAt = when (newStatus) {
			ToDoStatus.IN_PROGRESS -> null
			ToDoStatus.COMPLETE -> currentDate
		}
		onUpdateStatus(completedAt, newStatus)
	}
}