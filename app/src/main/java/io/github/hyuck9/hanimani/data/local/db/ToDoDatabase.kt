package io.github.hyuck9.hanimani.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.hyuck9.hanimani.data.entity.Task
import io.github.hyuck9.hanimani.data.local.db.dao.TaskDao

@Database(
	entities = [Task::class],
	version = 1,
	exportSchema = false
)
abstract class ToDoDatabase: RoomDatabase() {

	abstract fun taskDao(): TaskDao

	companion object {
		const val DATABASE_NAME = "HaniMani.db"
	}
}