package io.github.hyuck9.hanimani.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.hyuck9.hanimani.data.entity.ToDoEntity

@Dao
interface ToDoDao {

	@Query("SELECT * FROM ToDoEntity")
	suspend fun getAll(): List<ToDoEntity>

	@Query("SELECT * FROM ToDoEntity WHERE id = :id")
	suspend fun getById(id: Long): ToDoEntity?

	@Insert
	suspend fun insert(toDoEntity: ToDoEntity): Long

	@Insert
	suspend fun insert(toDoEntities: List<ToDoEntity>)

	@Query("DELETE FROM ToDoEntity WHERE id = :id")
	suspend fun delete(id: Long)

	@Query("DELETE FROM ToDoEntity")
	suspend fun deleteAll()

	@Update
	suspend fun update(toDoEntity: ToDoEntity)
}