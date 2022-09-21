package io.github.hyuck9.hanimani.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

  @Provides
  @Singleton
  fun provideIODispatcher(): CoroutineDispatcher {
    return Dispatchers.IO
  }

}
