package io.github.hyuck9.hanimani.features.theme.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.hyuck9.hanimani.features.theme.data.IThemeEnvironment
import io.github.hyuck9.hanimani.features.theme.data.ThemeEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class ThemeModule {

	@Binds
	abstract fun provideEnvironment(
		environment: ThemeEnvironment
	): IThemeEnvironment

}