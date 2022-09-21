package io.github.hyuck9.hanimani.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoEntity(
	@PrimaryKey(autoGenerate = true) val id: Long = 0,
	val title: String,
	val description: String,
	val hasCompleted: Boolean = false
)