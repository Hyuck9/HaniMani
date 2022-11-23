package io.github.hyuck9.hanimani.model

import java.time.LocalDateTime

data class ToDoTask(
	val id: String = "",
	val name: String = "",
	val status: ToDoStatus = ToDoStatus.IN_PROGRESS,
	val completedAt: LocalDateTime? = null,
	val createdAt: LocalDateTime = LocalDateTime.now(),
	val updatedAt: LocalDateTime = LocalDateTime.now(),
)