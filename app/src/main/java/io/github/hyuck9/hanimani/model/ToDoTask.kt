package io.github.hyuck9.hanimani.model

import java.time.LocalDateTime
import java.util.*

data class ToDoTask(
	val id: String = UUID.randomUUID().toString(),
	val name: String = "",
	val status: ToDoStatus = ToDoStatus.IN_PROGRESS,
	val completedAt: LocalDateTime? = null,
	val createdAt: LocalDateTime = LocalDateTime.now(),
	val updatedAt: LocalDateTime = LocalDateTime.now(),
)