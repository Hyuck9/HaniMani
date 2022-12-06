package io.github.hyuck9.hanimani.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.common.data.preference.FontSizePreferenceSerializer
import io.github.hyuck9.hanimani.common.data.preference.TextAlignPreferenceSerializer
import io.github.hyuck9.hanimani.common.data.preference.ThemePreferenceSerializer
import io.github.hyuck9.hanimani.common.data.preference.model.FontSizePreference
import io.github.hyuck9.hanimani.common.data.preference.model.TextAlignPreference
import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import javax.inject.Singleton

private const val THEME_NAME = "hanimani-theme-preference.pb"
private const val TEXT_ALIGN_NAME = "hanimani-text-align-preference.pb"
private const val FONT_SIZE_NAME = "hanimani-font-size-preference.pb"

private val Context.themeDataStore: DataStore<ThemePreference> by dataStore(
	fileName = THEME_NAME,
	serializer = ThemePreferenceSerializer
)

private val Context.textAlignDataStore: DataStore<TextAlignPreference> by dataStore(
	fileName = TEXT_ALIGN_NAME,
	serializer = TextAlignPreferenceSerializer
)

private val Context.fontSizeDataStore: DataStore<FontSizePreference> by dataStore(
	fileName = FONT_SIZE_NAME,
	serializer = FontSizePreferenceSerializer
)

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
