package io.github.hyuck9.hanimani.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.hyuck9.hanimani.common.data.preference.ThemePreferenceSerializer
import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import javax.inject.Singleton

private const val THEME_NAME = "hanimani-theme-preference.pb"

private val Context.themeDataStore: DataStore<ThemePreference> by dataStore(
	fileName = THEME_NAME,
	serializer = ThemePreferenceSerializer
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

	@Singleton
	@Provides
	fun provideThemeDataStore(
		@ApplicationContext context: Context
	): DataStore<ThemePreference> = context.themeDataStore

}
