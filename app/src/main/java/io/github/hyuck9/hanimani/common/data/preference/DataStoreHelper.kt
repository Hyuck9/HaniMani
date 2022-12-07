package io.github.hyuck9.hanimani.common.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import io.github.hyuck9.hanimani.common.data.preference.model.FontSizePreference
import io.github.hyuck9.hanimani.common.data.preference.model.TextAlignPreference
import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val THEME_NAME = "hanimani-theme-preference.pb"
private const val TEXT_ALIGN_NAME = "hanimani-text-align-preference.pb"
private const val FONT_SIZE_NAME = "hanimani-font-size-preference.pb"
private const val PREFERENCE_NAME = "hanimani-preference.pb"

val Context.themeDataStore: DataStore<ThemePreference> by dataStore(
	fileName = THEME_NAME,
	serializer = ThemePreferenceSerializer
)

val Context.textAlignDataStore: DataStore<TextAlignPreference> by dataStore(
	fileName = TEXT_ALIGN_NAME,
	serializer = TextAlignPreferenceSerializer
)

val Context.fontSizeDataStore: DataStore<FontSizePreference> by dataStore(
	fileName = FONT_SIZE_NAME,
	serializer = FontSizePreferenceSerializer
)

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

suspend fun Context.writeBoolean(key: String, value: Boolean) {
	dataStore.edit { pref ->
		pref[booleanPreferencesKey(key)] = value
	}
}

fun Context.readBoolean(key: String, defaultValue: Boolean = false): Flow<Boolean> =
	dataStore.data.map { pref ->
		pref[booleanPreferencesKey(key)] ?: defaultValue
	}