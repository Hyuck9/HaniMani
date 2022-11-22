package io.github.hyuck9.hanimani.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.common.data.local.TasksDao
import io.github.hyuck9.hanimani.common.data.repository.DefaultTasksRepository
import io.github.hyuck9.hanimani.common.data.repository.TasksRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideTasksRepository(
		tasksDao: TasksDao,
		coroutineDispatcher: CoroutineDispatcher
	): TasksRepository = DefaultTasksRepository(tasksDao, coroutineDispatcher)

}