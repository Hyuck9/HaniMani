package io.github.hyuck9.hanimani.common.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.common.data.local.model.TrashEntity

@Database(
	entities = [TaskEntity::class, TrashEntity::class],
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
					"CREATE TABLE trash (taskId TEXT NOT NULL, " +
							"taskName TEXT NOT NULL, " +
							"taskStatus TEXT NOT NULL, " +
							"taskOrder INTEGER NOT NULL, " +
							"completedAt INTEGER, " +
							"createdAt INTEGER NOT NULL, " +
							"updatedAt INTEGER NOT NULL, " +
							"deletedAt INTEGER NOT NULL, " +
							"PRIMARY KEY(taskId))"
				)
			}
		}
	}
}