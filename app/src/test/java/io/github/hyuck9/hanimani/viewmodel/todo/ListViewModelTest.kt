package io.github.hyuck9.hanimani.viewmodel.todo

import io.github.hyuck9.hanimani.data.entity.Task
import io.github.hyuck9.hanimani.data.repository.TestToDoRepository
import io.github.hyuck9.hanimani.domain.todo.*
import io.github.hyuck9.hanimani.presentation.list.ListViewModel
import io.github.hyuck9.hanimani.presentation.list.ToDoListState
import io.github.hyuck9.hanimani.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * [ListViewModel]을 테스트하기 위한 Unit Test Class
 *
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test Item Update
 * 4. test Item Delete All
 *
 */
@ExperimentalCoroutinesApi
internal class ListViewModelTest: ViewModelTest() {

	private lateinit var viewModel: ListViewModel

	private val testToDoRepository = TestToDoRepository()

	private val insertToDoListUseCase = InsertToDoListUseCase(testToDoRepository)
	private val getToDoItemUseCase = GetToDoItemUseCase(testToDoRepository)

	private val getToDoListUseCase = GetToDoListUseCase(testToDoRepository)
	private val updateToDoItemUseCase = UpdateToDoItemUseCase(testToDoRepository)
	private val deleteAllToDoListUseCase = DeleteAllToDoListUseCase(testToDoRepository)

	private val mockList = (0 until 10).map {
		Task(
			id = it.toLong(),
			title = "title $it",
			description = "description $it",
			isCompleted = false
		)
	}

	@Before
	fun init() {
		initData()
	}

	private fun initData() = runTest {
		viewModel = ListViewModel(getToDoListUseCase, updateToDoItemUseCase, deleteAllToDoListUseCase)
		insertToDoListUseCase(mockList)
	}

	// Test : 입력된 데이터를 불러와서 검증한다.
	@Test
	fun `test viewModel fetch`(): Unit = runTest {
		val testObservable = viewModel.toDoListLiveData.test()
		viewModel.fetchData()
		testObservable.assertValueSequence(
			listOf(
				ToDoListState.UnInitialized,
				ToDoListState.Loading,
				ToDoListState.Success(mockList)
			)
		)
	}

	// Test : 데이터를 업데이트 했을 때 잘 반영되는가
	@Test
	fun `test Item Update`(): Unit = runTest {
		val todo = Task(
			id = 1,
			title = "title 1",
			description = "description 1",
			isCompleted = true
		)
		viewModel.updateEntity(todo)
		assert((getToDoItemUseCase(todo.id)?.isCompleted ?: false) == todo.isCompleted)
	}

	// Test : 데이터를 다 날렸을 때 빈상태로 보여지는가
	@Test
	fun `test Item Delete All`(): Unit = runTest {
		val testObservable = viewModel.toDoListLiveData.test()
		viewModel.deleteAll()
		testObservable.assertValueSequence(
			listOf(
				ToDoListState.UnInitialized,
				ToDoListState.Loading,
				ToDoListState.Success(listOf())
			)
		)
	}

}