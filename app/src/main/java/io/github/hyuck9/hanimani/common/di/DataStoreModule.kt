package io.github.hyuck9.hanimani.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.common.data.preference.*
import io.github.hyuck9.hanimani.common.data.preference.model.FontSizePreference
import io.github.hyuck9.hanimani.common.data.preference.model.TextAlignPreference
import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

	@Singleton
	@Provides
	fun provideThemeDataStore(
		@ApplicationContext context: Context
	): DataStore<ThemePreference> = context.themeDataStore

	@Singleton
	@Provides
	fun provideTextAlignDataStore(
		@ApplicationContext context: Context
	): DataStore<TextAlignPreference> = context.textAlignDataStore

	@Singleton
	@Provides
	fun provideFontSizeDataStore(
		@ApplicationContext context: Context
	): DataStore<FontSizePreference> = context.fontSizeDataStore

}
