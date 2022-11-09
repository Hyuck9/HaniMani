package io.github.hyuck9.hanimani.common.data.local

import androidx.room.Dao
import androidx.room.Query
import io.github.hyuck9.hanimani.common.data.local.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

	@Query("SELECT * FROM Tasks")
	fun observeTasks(): Flow<List<Task>>

	@Query("SELECT * FROM Tasks WHERE taskId = :taskId")
	fun observeTaskById(taskId: String): Flow<Task>

	@Query("SELECT * FROM Tasks")
	suspend fun getTasks(): List<Task>

	@Query("SELECT * FROM Tasks WHERE taskId = :taskId")
	suspend fun getTaskById(taskId: String): Task?

}