package io.github.hyuck9.hanimani.common.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity

@Database(
	entities = [TaskEntity::class],
	version = 2,
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
				.addMigrations(MIGRATION_1_2)
				.build()

		private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
			override fun migrate(database: SupportSQLiteDatabase) {
				database.execSQL(
					"ALTER TABLE tasks ADD COLUMN isDelete INTEGER NOT NULL DEFAULT 0"
				)
			}
		}
	}
}