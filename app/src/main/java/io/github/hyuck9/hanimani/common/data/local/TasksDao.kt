package io.github.hyuck9.hanimani.common.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.common.data.local.model.TrashEntity
import io.github.hyuck9.hanimani.model.ToDoStatus
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface TasksDao {

//	@Query("SELECT * FROM Tasks WHERE isDelete = 0 ORDER BY taskStatus DESC, taskOrder")
//	fun observeTasks(): Flow<List<TaskEntity>>
	@Query("SELECT * FROM Tasks ORDER BY taskStatus DESC, taskOrder")
	fun observeTasks(): Flow<List<TaskEntity>>

	@Query("SELECT * FROM Tasks WHERE taskId = :taskId")
	fun observeTaskById(taskId: String): Flow<TaskEntity>

	@Query("SELECT * FROM Tasks WHERE taskId = :taskId")
	suspend fun getTaskById(taskId: String): TaskEntity?

	/**
	 * Insert or Update [TaskEntity]
	 */
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveTask(taskEntity: TaskEntity)

	@Query("UPDATE Tasks SET taskStatus = :status, completedAt = :completedAt, updatedAt = :updatedAt WHERE taskId = :taskId")
	suspend fun updateTaskStatus(taskId: String, status: ToDoStatus, completedAt: LocalDateTime?, updatedAt: LocalDateTime)

	@Query("UPDATE Tasks SET taskOrder = :order, updatedAt = :updatedAt WHERE taskId = :taskId")
	suspend fun updateOrder(taskId: String, order: Int, updatedAt: LocalDateTime)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun updateTasks(tasks: List<TaskEntity>)

	@Query("UPDATE Tasks SET taskName = :name, updatedAt = :updatedAt WHERE taskId = :taskId")
	suspend fun updateTaskName(taskId: String, name: String, updatedAt: LocalDateTime)

	@Query("DELETE FROM tasks WHERE taskId = :taskId")
	suspend fun deleteTaskById(taskId: String)

	@Query("DELETE FROM tasks")
	suspend fun deleteAllTasks()

	@Query("DELETE FROM tasks WHERE taskStatus = :status")
	suspend fun deleteTasksByStatus(status: ToDoStatus)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveTrash(trashEntity: TrashEntity)

	@Query("DELETE FROM trash WHERE taskId = :taskId")
	suspend fun deleteTrashById(taskId: String)

	@Query("DELETE FROM trash")
	suspend fun deleteAllTrash()

}