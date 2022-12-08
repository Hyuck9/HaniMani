package io.github.hyuck9.hanimani.features.edittask.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.hyuck9.hanimani.features.edittask.data.EditTaskEnvironment
import io.github.hyuck9.hanimani.features.edittask.data.IEditTaskEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class EditTaskModule {

	@Binds
	abstract fun provideEnvironment(
		environment: EditTaskEnvironment
	): IEditTaskEnvironment

}