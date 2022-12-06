package io.github.hyuck9.hanimani.features.settings.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.hyuck9.hanimani.features.settings.data.ISettingsEnvironment
import io.github.hyuck9.hanimani.features.settings.data.SettingsEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingsModule {

	@Binds
	abstract fun provideEnvironment(
		environment: SettingsEnvironment
	): ISettingsEnvironment

}