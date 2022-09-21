package io.github.hyuck9.hanimani.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.hyuck9.hanimani.data.entity.ToDoEntity
import io.github.hyuck9.hanimani.data.local.db.dao.ToDoDao

@Database(
	entities = [ToDoEntity::class],
	version = 1,
	exportSchema = false
)
abstract class ToDoDatabase: RoomDatabase() {

	abstract fun toDoDao(): ToDoDao

	companion object {
		const val DATABASE_NAME = "HaniMani.db"
	}
}