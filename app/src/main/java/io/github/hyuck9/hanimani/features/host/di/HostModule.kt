package io.github.hyuck9.hanimani.features.host.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.hyuck9.hanimani.features.host.data.HostEnvironment
import io.github.hyuck9.hanimani.features.host.data.IHostEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class HostModule {

	@Binds
	abstract fun provideEnvironment(
		environment: HostEnvironment
	): IHostEnvironment

}