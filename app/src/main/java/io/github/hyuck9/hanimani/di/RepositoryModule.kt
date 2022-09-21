package io.github.hyuck9.hanimani.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.data.repository.DefaultToDoRepository
import io.github.hyuck9.hanimani.data.repository.ToDoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideDefaultToDoRepository(): ToDoRepository = DefaultToDoRepository()
}