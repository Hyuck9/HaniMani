package io.github.hyuck9.hanimani.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.hyuck9.hanimani.data.entity.ToDoEntity
import io.github.hyuck9.hanimani.domain.todo.DeleteAllToDoItemUseCase
import io.github.hyuck9.hanimani.domain.todo.GetToDoListUseCase
import io.github.hyuck9.hanimani.domain.todo.UpdateToDoUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 필요한 UseCase
 * 1. [GetToDoListUseCase]
 * 2. [UpdateToDoUseCase]
 * 3. [DeleteAllToDoItemUseCase]
 */
internal class ListViewModel(
	private val getToDoListUseCase: GetToDoListUseCase,
	private val updateToDoUseCase: UpdateToDoUseCase,
	private val deleteAllToDoItemUseCase: DeleteAllToDoItemUseCase,
): ViewModel() {

	private val _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
	val toDoListLiveData: LiveData<ToDoListState> = _toDoListLiveData

	fun fetchData(): Job = viewModelScope.launch {
		_toDoListLiveData.postValue(ToDoListState.Loading)
		_toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
	}

	fun updateEntity(toDoEntity: ToDoEntity) = viewModelScope.launch {
		updateToDoUseCase(toDoEntity)
	}

	fun deleteAll() = viewModelScope.launch {
		_toDoListLiveData.postValue(ToDoListState.Loading)
		deleteAllToDoItemUseCase()
		_toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
	}
}