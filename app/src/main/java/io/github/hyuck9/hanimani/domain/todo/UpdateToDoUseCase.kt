package io.github.hyuck9.hanimani.domain.todo

import io.github.hyuck9.hanimani.data.entity.ToDoEntity
import io.github.hyuck9.hanimani.data.repository.ToDoRepository
import io.github.hyuck9.hanimani.domain.UseCase

internal class UpdateToDoUseCase(
	private val toDoRepository: ToDoRepository
): UseCase {

	suspend operator fun invoke(toDoEntity: ToDoEntity): Boolean {
		return toDoRepository.updateToDoItem(toDoEntity)
	}

}