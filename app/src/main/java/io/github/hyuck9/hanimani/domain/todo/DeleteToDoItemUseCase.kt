package io.github.hyuck9.hanimani.domain.todo

import io.github.hyuck9.hanimani.data.repository.ToDoRepository
import io.github.hyuck9.hanimani.domain.UseCase
import javax.inject.Inject


internal class DeleteToDoItemUseCase @Inject constructor(
	private val toDoRepository: ToDoRepository
): UseCase {

	suspend operator fun invoke(itemId: Long): Boolean {
		return toDoRepository.deleteToDoItem(itemId)
	}

}