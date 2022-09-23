package io.github.hyuck9.hanimani.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
	@PrimaryKey(autoGenerate = true) val id: Long = 0,
	val title: String,
	val description: String,
	val isCompleted: Boolean = false
)