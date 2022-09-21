package io.github.hyuck9.hanimani.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.data.local.db.dao.ToDoDao
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
		toDoDao: ToDoDao,
		ioDispatcher: CoroutineDispatcher
	): ToDoRepository = DefaultToDoRepository(toDoDao, ioDispatcher)
}