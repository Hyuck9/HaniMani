package io.github.hyuck9.hanimani.common.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "tasks")
data class TaskEntity(
	@PrimaryKey
	@ColumnInfo(name = "taskId")
	val id: String = UUID.randomUUID().toString(),
	@ColumnInfo(name = "title")
	val title: String = "",
	@ColumnInfo(name = "description")
	val description: String = "",
	@ColumnInfo(name = "completed")
	val isCompleted: Boolean = false,
	@ColumnInfo(name = "completedAt")
	val completedAt: LocalDateTime? = null,
	@ColumnInfo(name = "createdAt")
	val createdAt: LocalDateTime = LocalDateTime.now(),
	@ColumnInfo(name = "updatedAt")
	val updatedAt: LocalDateTime = LocalDateTime.now(),
)