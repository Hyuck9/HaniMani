package io.github.hyuck9.hanimani.common.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.common.data.local.HaniManiDatabase
import io.github.hyuck9.hanimani.common.data.local.TasksDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

	@Singleton
	@Provides
	fun provideTasksDao(@ApplicationContext context: Context): TasksDao {
		return HaniManiDatabase.getInstance(context).tasksDao()
	}

}