package io.github.hyuck9.hanimani.features.tasks.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.hyuck9.hanimani.features.tasks.data.ITasksEnvironment
import io.github.hyuck9.hanimani.features.tasks.data.TasksEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class TasksModule {

	@Binds
	abstract fun provideEnvironment(
		environment: TasksEnvironment
	): ITasksEnvironment

}