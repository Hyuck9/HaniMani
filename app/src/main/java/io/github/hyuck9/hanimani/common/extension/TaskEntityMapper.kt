package io.github.hyuck9.hanimani.common.extension

import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity
import io.github.hyuck9.hanimani.common.data.local.model.TrashEntity
import io.github.hyuck9.hanimani.model.ToDoTask

fun TaskEntity.toToDoTask(): ToDoTask {
	return ToDoTask(
		id = id,
		name = name,
		status = status,
		order = order,
		completedAt = completedAt,
		createdAt = createdAt,
		updatedAt = updatedAt
	)
}

fun TaskEntity.toTrashEntity(): TrashEntity {
	return TrashEntity(
		id = id,
		name = name,
		status = status,
		order = order,
		completedAt = completedAt,
		createdAt = createdAt,
		updatedAt = updatedAt
	)
}

fun ToDoTask.toTaskEntity(): TaskEntity {
	return TaskEntity(
		id = id,
		name = name,
		status = status,
		order = order,
		completedAt = completedAt,
		createdAt = createdAt,
		updatedAt = updatedAt
	)
}

fun ToDoTask.toTrashEntity(): TrashEntity {
	return TrashEntity(
		id = id,
		name = name,
		status = status,
		order = order,
		completedAt = completedAt,
		createdAt = createdAt,
		updatedAt = updatedAt
	)
}

fun TrashEntity.toTaskEntity(): TaskEntity {
	return TaskEntity(
		name = name,
		status = status,
		order = order,
		completedAt = completedAt,
		createdAt = createdAt,
		updatedAt = updatedAt
	)
}

fun List<TaskEntity>.toToDoTasks(): List<ToDoTask> {
	return map { it.toToDoTask() }
}