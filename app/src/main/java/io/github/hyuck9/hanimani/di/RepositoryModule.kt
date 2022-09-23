package io.github.hyuck9.hanimani.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.data.local.db.dao.TaskDao
import io.github.hyuck9.hanimani.data.repository.DefaultToDoRepository
import io.github.hyuck9.hanimani.data.repository.ToDoRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideDefaultToDoRepository(
		taskDao: TaskDao,
		ioDispatcher: CoroutineDispatcher
	): ToDoRepository = DefaultToDoRepository(taskDao, ioDispatcher)
}