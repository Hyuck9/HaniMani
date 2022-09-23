package io.github.hyuck9.hanimani.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.hyuck9.hanimani.data.entity.Task

@Dao
interface TaskDao {

	@Query("SELECT * FROM Task")
	suspend fun getAll(): List<Task>

	@Query("SELECT * FROM Task WHERE id = :id")
	suspend fun getById(id: Long): Task?

	@Insert
	suspend fun insert(task: Task): Long

	@Insert
	suspend fun insert(toDoEntities: List<Task>)

	@Query("DELETE FROM Task WHERE id = :id")
	suspend fun delete(id: Long)

	@Query("DELETE FROM Task")
	suspend fun deleteAll()

	@Update
	suspend fun update(task: Task)
}