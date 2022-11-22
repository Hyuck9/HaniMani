package io.github.hyuck9.hanimani.features.tasks.ui

import io.github.hyuck9.hanimani.common.data.local.model.TaskEntity

data class TasksState(
	val items: List<TaskEntity> = emptyList(),
)