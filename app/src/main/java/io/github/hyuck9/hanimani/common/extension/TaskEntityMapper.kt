package io.github.hyuck9.hanimani.common.extension

import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.model.ToDoTask

fun TaskEntity.toToDoTask(): ToDoTask {
	return ToDoTask(
		id = id,
		name = name,
		status = status,
		completedAt = completedAt,
		createdAt = createdAt,
		updatedAt = updatedAt
	)
}

fun List<TaskEntity>.toToDoTasks(): List<ToDoTask> {
	return map { it.toToDoTask() }
}