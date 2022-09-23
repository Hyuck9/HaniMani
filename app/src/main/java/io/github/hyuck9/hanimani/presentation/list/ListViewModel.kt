package io.github.hyuck9.hanimani.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.data.entity.Task
import io.github.hyuck9.hanimani.domain.todo.DeleteAllToDoListUseCase
import io.github.hyuck9.hanimani.domain.todo.GetToDoListUseCase
import io.github.hyuck9.hanimani.domain.todo.UpdateToDoItemUseCase
import io.github.hyuck9.hanimani.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 필요한 UseCase
 * 1. [GetToDoListUseCase]
 * 2. [UpdateToDoItemUseCase]
 * 3. [DeleteAllToDoListUseCase]
 */
@HiltViewModel
internal class ListViewModel @Inject constructor(
	private val getToDoListUseCase: GetToDoListUseCase,
	private val updateToDoItemUseCase: UpdateToDoItemUseCase,
	private val deleteAllToDoListUseCase: DeleteAllToDoListUseCase,
): BaseViewModel() {

	private val _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
	val toDoListLiveData: LiveData<ToDoListState> = _toDoListLiveData

	override fun fetchData(): Job = viewModelScope.launch {
		_toDoListLiveData.postValue(ToDoListState.Loading)
		_toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
	}

	fun updateEntity(task: Task) = viewModelScope.launch {
		updateToDoItemUseCase(task)
	}

	fun deleteAll() = viewModelScope.launch {
		_toDoListLiveData.postValue(ToDoListState.Loading)
		deleteAllToDoListUseCase()
		_toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
	}
}