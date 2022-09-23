package io.github.hyuck9.hanimani.domain.todo

import io.github.hyuck9.hanimani.data.entity.Task
import io.github.hyuck9.hanimani.data.repository.ToDoRepository
import io.github.hyuck9.hanimani.domain.UseCase
import javax.inject.Inject

internal class InsertToDoListUseCase @Inject constructor(
	private val toDoRepository: ToDoRepository
): UseCase {

	suspend operator fun invoke(toDoList: List<Task>) {
		return toDoRepository.insertToDoList(toDoList)
	}

}