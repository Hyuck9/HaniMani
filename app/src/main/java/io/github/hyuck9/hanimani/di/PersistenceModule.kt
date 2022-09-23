package io.github.hyuck9.hanimani.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.data.local.db.ToDoDatabase
import io.github.hyuck9.hanimani.data.local.db.dao.TaskDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

	@Provides
	@Singleton
	fun provideAppDatabase(
		@ApplicationContext context: Context
	): ToDoDatabase = Room
		.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DATABASE_NAME)
		.fallbackToDestructiveMigration()	// TODO: DB 변경이 있을 경우 기존 데이터 삭제 후 재생성 (테스트 용도로 사용)
		.build()

	@Provides
	@Singleton
	fun provideToDoDao(toDoDatabase: ToDoDatabase): TaskDao = toDoDatabase.taskDao()

}