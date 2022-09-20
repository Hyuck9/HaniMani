package io.github.hyuck9.hanimani.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.github.hyuck9.hanimani.data.repository.TestToDoRepository
import io.github.hyuck9.hanimani.data.repository.ToDoRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
	components = [SingletonComponent::class],
	replaces = [RepositoryModule::class]
)
abstract class TestRepositoryModule {

	@Singleton
	@Binds
	abstract fun bindRepository(repo: TestToDoRepository): ToDoRepository

}