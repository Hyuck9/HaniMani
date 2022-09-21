package io.github.hyuck9.hanimani.presentation.detail

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hyuck9.hanimani.data.entity.ToDoEntity
import io.github.hyuck9.hanimani.domain.todo.DeleteToDoItemUseCase
import io.github.hyuck9.hanimani.domain.todo.GetToDoItemUseCase
import io.github.hyuck9.hanimani.domain.todo.InsertToDoItemUseCase
import io.github.hyuck9.hanimani.domain.todo.UpdateToDoItemUseCase
import io.github.hyuck9.hanimani.presentation.BaseViewModel
import io.github.hyuck9.hanimani.presentation.detail.DetailViewModel.AssistedFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModelProvider]를 정의해주고, 그 내부에서 parameter로 정의한 [AssistedFactory]와 전달하고자하는 객체를 전달해줘서
 * [ViewModelProvider] 내부에서 [AssistedFactory] 객체를 호출하는 형태로 구현.
 *  - AssistedInject 기능을 사용하려면 [HiltViewModel]을 정의하면 안되고,
 *  - 기존 [Inject] 어노테이션이 아닌 [AssistedInject] 어노테이션을 Constructor에 달아줘야 한다.
 *  - Graph Module이 아닌, [AssistedFactory]를 통해 객체 주입 받는다는것을 명시하기 위해 [Assisted]를 달아준다.
 */
internal class DetailViewModel @AssistedInject constructor(
	@Assisted var detailMode: DetailMode,
	@Assisted var id: Long = -1,
	private val getToDoItemUseCase: GetToDoItemUseCase,
	private val deleteToDoItemUseCase: DeleteToDoItemUseCase,
	private val updateToDoItemUseCase: UpdateToDoItemUseCase,
	private val insertToDoItemUseCase: InsertToDoItemUseCase
): BaseViewModel() {

	private val _toDoDetailLiveData = MutableLiveData<ToDoDetailState>(ToDoDetailState.UnInitialized)
	val toDoDetailLiveData: LiveData<ToDoDetailState> = _toDoDetailLiveData

	override fun fetchData() = viewModelScope.launch {
		when (detailMode) {
			DetailMode.WRITE -> {
				_toDoDetailLiveData.postValue(ToDoDetailState.Write)
			}
			DetailMode.DETAIL -> {
				_toDoDetailLiveData.postValue(ToDoDetailState.Loading)
				try {
					getToDoItemUseCase(id)?.let {
						_toDoDetailLiveData.postValue(ToDoDetailState.Success(it))
					} ?: kotlin.run {
						_toDoDetailLiveData.postValue(ToDoDetailState.Error)
					}
				} catch (e: Exception) {
					e.printStackTrace()
					_toDoDetailLiveData.postValue(ToDoDetailState.Error)
				}
			}
		}
	}

	fun deleteToDo() = viewModelScope.launch {
		_toDoDetailLiveData.postValue(ToDoDetailState.Loading)
		try {
			deleteToDoItemUseCase(id)
			_toDoDetailLiveData.postValue(ToDoDetailState.Delete)
		} catch (e: Exception) {
			e.printStackTrace()
			_toDoDetailLiveData.postValue(ToDoDetailState.Error)
		}
	}

	fun writeToDo(title: String, description: String) = viewModelScope.launch {
		_toDoDetailLiveData.postValue(ToDoDetailState.Loading)
		when (detailMode) {
			DetailMode.WRITE -> {
				try {
					val toDoEntity = ToDoEntity(
						title = title,
						description = description
					)
					id = insertToDoItemUseCase(toDoEntity)
					_toDoDetailLiveData.postValue(ToDoDetailState.Success(toDoEntity))
					detailMode = DetailMode.DETAIL
				} catch (e: Exception) {
					e.printStackTrace()
					_toDoDetailLiveData.postValue(ToDoDetailState.Error)
				}
			}
			DetailMode.DETAIL -> {
				try {
					getToDoItemUseCase(id)?.let {
						val updateToDoEntity = it.copy(
							title = title,
							description = description
						)
						updateToDoItemUseCase(updateToDoEntity)
						_toDoDetailLiveData.postValue(ToDoDetailState.Success(updateToDoEntity))
					} ?: kotlin.run {
						_toDoDetailLiveData.postValue(ToDoDetailState.Error)
					}
				} catch (e: Exception) {
					e.printStackTrace()
					_toDoDetailLiveData.postValue(ToDoDetailState.Error)
				}
			}
		}
	}


	@dagger.assisted.AssistedFactory
	interface AssistedFactory {
		fun create(detailMode: DetailMode, id: Long = -1): DetailViewModel
	}

	companion object {
		fun provideFactory(
			assistedFactory: AssistedFactory,
			detailMode: DetailMode,
			id: Long = -1
		): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

			@Suppress("UNCHECKED_CAST")
			override fun <T : ViewModel> create(modelClass: Class<T>): T {
				return assistedFactory.create(detailMode, id) as T
			}

		}
	}

}