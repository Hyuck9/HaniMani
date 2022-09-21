package io.github.hyuck9.hanimani.viewmodel.todo

import io.github.hyuck9.hanimani.data.entity.ToDoEntity
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
 * 1. test viewModel fetch
 * 2. test Item Insert
 *
 */
@ExperimentalCoroutinesApi
internal class DetailViewModelForWriteTest: ViewModelTest() {

	private val id = 0L

	private lateinit var detailViewModel: DetailViewModel
	private lateinit var listViewModel: ListViewModel

	private val testToDoRepository = TestToDoRepository()

	private val insertToDoItemUseCase = InsertToDoItemUseCase(testToDoRepository)
	private val getToDoItemUseCase = GetToDoItemUseCase(testToDoRepository)
	private val deleteToDoItemUseCase = DeleteToDoItemUseCase(testToDoRepository)
	private val updateToDoItemUseCase = UpdateToDoItemUseCase(testToDoRepository)
	private val getToDoListUseCase = GetToDoListUseCase(testToDoRepository)
	private val deleteAllToDoListUseCase = DeleteAllToDoListUseCase(testToDoRepository)

	private val todo = ToDoEntity(
		id = id,
		title = "title $id",
		description = "description $id",
		hasCompleted = false
	)

	@Before
	fun init() {
		initData()
	}

	private fun initData() = runTest {
		detailViewModel = DetailViewModel(DetailMode.WRITE, id, getToDoItemUseCase, deleteToDoItemUseCase, updateToDoItemUseCase, insertToDoItemUseCase)
		listViewModel = ListViewModel(getToDoListUseCase, updateToDoItemUseCase, deleteAllToDoListUseCase)
	}

	@Test
	fun `test viewModel fetch`(): Unit = runTest {
		val testObservable = detailViewModel.toDoDetailLiveData.test()
		detailViewModel.fetchData()
		testObservable.assertValueSequence(
			listOf(
				ToDoDetailState.UnInitialized,
				ToDoDetailState.Write
			)
		)
	}

	@Test
	fun `test Item Insert`(): Unit = runTest {
		val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
		val listTestObservable = listViewModel.toDoListLiveData.test()

		detailViewModel.writeToDo(
			title = todo.title,
			description = todo.description
		)

		detailTestObservable.assertValueSequence(
			listOf(
				ToDoDetailState.UnInitialized,
				ToDoDetailState.Loading,
				ToDoDetailState.Success(todo)
			)
		)
		assert(detailViewModel.detailMode == DetailMode.DETAIL)
		assert(detailViewModel.id == id)

		listViewModel.fetchData()
		listTestObservable.assertValueSequence(
			listOf(
				ToDoListState.UnInitialized,
				ToDoListState.Loading,
				ToDoListState.Success(
					listOf(
						todo
					)
				)
			)
		)
	}


}