package io.github.hyuck9.hanimani.common.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

	@Query("SELECT * FROM Tasks")
	fun observeTasks(): Flow<List<TaskEntity>>

	@Query("SELECT * FROM Tasks WHERE taskId = :taskId")
	fun observeTaskById(taskId: String): Flow<TaskEntity>

	@Query("SELECT * FROM Tasks")
	suspend fun getTasks(): List<TaskEntity>

	@Query("SELECT * FROM Tasks WHERE taskId = :taskId")
	suspend fun getTaskById(taskId: String): TaskEntity?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveTask(taskEntity: TaskEntity)
}