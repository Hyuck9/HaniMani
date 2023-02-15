package io.github.hyuck9.hanimani.common.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.hyuck9.hanimani.model.ToDoStatus
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "tasks")
data class TaskEntity(
	@PrimaryKey
	@ColumnInfo(name = "taskId")
	val id: String = UUID.randomUUID().toString(),
	@ColumnInfo(name = "taskName")
	val name: String = "",
	@ColumnInfo(name = "taskStatus")
	val status: ToDoStatus,
	@ColumnInfo(name = "isDelete")
	val isDelete: Boolean = false,
	@ColumnInfo(name = "taskOrder")
	val order: Int = 0,
	@ColumnInfo(name = "completedAt")
	val completedAt: LocalDateTime? = null,
	@ColumnInfo(name = "createdAt")
	val createdAt: LocalDateTime = LocalDateTime.now(),
	@ColumnInfo(name = "updatedAt")
	val updatedAt: LocalDateTime = LocalDateTime.now(),
)