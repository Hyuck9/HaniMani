package io.github.hyuck9.hanimani.domain.todo

import io.github.hyuck9.hanimani.data.repository.ToDoRepository
import io.github.hyuck9.hanimani.domain.UseCase


internal class DeleteAllToDoItemUseCase(
	private val toDoRepository: ToDoRepository
): UseCase {

	suspend operator fun invoke() {
		return toDoRepository.deleteAll()
	}

}