package io.github.hyuck9.hanimani.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.hyuck9.hanimani.livedata.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@HiltAndroidTest
@ExperimentalCoroutinesApi
internal abstract class ViewModelTest {

	@get:Rule
	val mockitoRule: MockitoRule = MockitoJUnit.rule()

	@get: Rule
	var instantExecutorRule = InstantTaskExecutorRule()

	@Mock
	private lateinit var context: Application

	private val dispatcher = UnconfinedTestDispatcher()	// TODO: StandardTestDispatcher 사용

	@Before
	fun setup() {
		Dispatchers.setMain(dispatcher)
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()	// MainDispatcher를 초기화 해주어야 메모리 누수가 발생하지 않음
	}

	protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T> {
		val testObserver = LiveDataTestObserver<T>()
		observeForever(testObserver)
		return testObserver
	}

}