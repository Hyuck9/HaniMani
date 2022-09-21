package io.github.hyuck9.hanimani.domain.todo

import io.github.hyuck9.hanimani.data.entity.ToDoEntity
import io.github.hyuck9.hanimani.data.repository.ToDoRepository
import io.github.hyuck9.hanimani.domain.UseCase
import javax.inject.Inject

internal class InsertToDoListUseCase @Inject constructor(
	private val toDoRepository: ToDoRepository
): UseCase {

	suspend operator fun invoke(toDoList: List<ToDoEntity>) {
		return toDoRepository.insertToDoList(toDoList)
	}

}