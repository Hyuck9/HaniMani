package io.github.hyuck9.hanimani.viewmodel.todo

import io.github.hyuck9.hanimani.data.entity.Task
import io.github.hyuck9.hanimani.data.repository.TestToDoRepository
import io.github.hyuck9.hanimani.domain.todo.*
import io.github.hyuck9.hanimani.presentation.detail.DetailMode
import io.github.hyuck9.hanimani.presentation.detail.DetailViewModel
import io.github.hyuck9.hanimani.presentation.detail.ToDoDetailState
import io.github.hyuck9.hanimani.presentation.list.ListViewModel
import io.github.hyuck9.hanimani.presentation.list.ToDoListState
import io.github.hyuck9.hanimani.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * [DetailViewModel]을 테스트하기 위한 Unit Test Class
 *
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test Item Delete
 * 4. test Item Update
 *
 */
@ExperimentalCoroutinesApi
internal class DetailViewModelTest: ViewModelTest() {

	private val id = 1L

	private lateinit var detailViewModel: DetailViewModel
	private lateinit var listViewModel: ListViewModel

	private val testToDoRepository = TestToDoRepository()

	private val insertToDoItemUseCase = InsertToDoItemUseCase(testToDoRepository)
	private val getToDoItemUseCase = GetToDoItemUseCase(testToDoRepository)
	private val deleteToDoItemUseCase = DeleteToDoItemUseCase(testToDoRepository)
	private val updateToDoItemUseCase = UpdateToDoItemUseCase(testToDoRepository)
	private val getToDoListUseCase = GetToDoListUseCase(testToDoRepository)
	private val deleteAllToDoListUseCase = DeleteAllToDoListUseCase(testToDoRepository)

	private val todo = Task(
		id = id,
		title = "title $id",
		description = "description $id",
		isCompleted = false
	)

	@Before
	fun init() {
		initData()
	}

	private fun initData() = runTest {
		detailViewModel = DetailViewModel(DetailMode.DETAIL, id, getToDoItemUseCase, deleteToDoItemUseCase, updateToDoItemUseCase, insertToDoItemUseCase)
		listViewModel = ListViewModel(getToDoListUseCase, updateToDoItemUseCase, deleteAllToDoListUseCase)
		insertToDoItemUseCase(todo)
	}

	@Test
	fun `test viewModel fetch`(): Unit = runTest {
		val testObservable = detailViewModel.toDoDetailLiveData.test()
		detailViewModel.fetchData()
		testObservable.assertValueSequence(
			listOf(
				ToDoDetailState.UnInitialized,
				ToDoDetailState.Loading,
				ToDoDetailState.Success(todo)
			)
		)
	}

	@Test
	fun `test Item Delete`(): Unit = runTest {
		val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
		detailViewModel.deleteToDo()
		detailTestObservable.assertValueSequence(
			listOf(
				ToDoDetailState.UnInitialized,
				ToDoDetailState.Loading,
				ToDoDetailState.Delete
			)
		)

		// 상세화면에서 Delete를 하면 더이상 데이터가 없기때문에 나가졌을때 데이터가 없는걸 보여주기 위해 리스트도 테스트
		val listTestObservable = listViewModel.toDoListLiveData.test()
		listViewModel.fetchData()
		listTestObservable.assertValueSequence(
			listOf(
				ToDoListState.UnInitialized,
				ToDoListState.Loading,
				ToDoListState.Success(listOf())
			)
		)
	}

	@Test
	fun `test Item Update`(): Unit = runTest {
		val testObservable = detailViewModel.toDoDetailLiveData.test()

		val updateTitle = "title 1 update"
		val updateDescription = "description 1 update"
		val updateToDo = todo.copy(
			title = updateTitle,
			description = updateDescription
		)

		detailViewModel.writeToDo(
			title = updateTitle,
			description = updateDescription
		)

		testObservable.assertValueSequence(
			listOf(
				ToDoDetailState.UnInitialized,
				ToDoDetailState.Loading,
				ToDoDetailState.Success(updateToDo)
			)
		)
	}


}