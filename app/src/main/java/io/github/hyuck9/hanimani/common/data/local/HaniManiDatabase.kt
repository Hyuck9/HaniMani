package io.github.hyuck9.hanimani.common.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.hyuck9.hanimani.common.data.local.model.Task

@Database(
	entities = [Task::class],
	version = 1,
	exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class HaniManiDatabase : RoomDatabase() {
	abstract fun tasksDao(): TasksDao

	companion object {
		private const val DATABASE_NAME = "HaniMani.db"

		@Volatile
		private var INSTANCE: HaniManiDatabase? = null

		fun getInstance(context: Context): HaniManiDatabase {
			return INSTANCE ?: synchronized(this) {
				INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
			}
		}

		private fun buildDatabase(context: Context): HaniManiDatabase =
			Room.databaseBuilder(
				context.applicationContext,
				HaniManiDatabase::class.java,
				DATABASE_NAME
			)
				.fallbackToDestructiveMigration()
				.build()
	}
}