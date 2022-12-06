package io.github.hyuck9.hanimani.common.data.preference

import androidx.datastore.core.DataStore
import io.github.hyuck9.hanimani.common.data.preference.model.FontSizePreference
import io.github.hyuck9.hanimani.common.data.preference.model.TextAlignPreference
import io.github.hyuck9.hanimani.common.data.preference.model.ThemePreference
import io.github.hyuck9.hanimani.common.extension.*
import io.github.hyuck9.hanimani.model.FontSize
import io.github.hyuck9.hanimani.model.TaskAlign
import io.github.hyuck9.hanimani.model.Theme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreferenceManager @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val themeDataStore: DataStore<ThemePreference>,
	private val textAlignDataStore: DataStore<TextAlignPreference>,
	private val fontSizeDataStore: DataStore<FontSizePreference>
) {
	fun getTheme(): Flow<Theme> = themeDataStore
		.data.map { it.themeColor.toTheme() }
		.catch { emit(Theme.SYSTEM) }
		.flowOn(ioDispatcher)

	fun getTaskAlign(): Flow<TaskAlign> = textAlignDataStore
		.data.map { it.taskAlign.toTaskAlign() }
		.catch { emit(TaskAlign.START) }
		.flowOn(ioDispatcher)

	fun getFontSize(): Flow<FontSize> = fontSizeDataStore
		.data.map { it.fontSize.toFontSize() }
		.catch { emit(FontSize.SMALL) }
		.flowOn(ioDispatcher)

	suspend fun setTheme(theme: Theme) = withContext(ioDispatcher) {
		themeDataStore.updateData {
			it.toBuilder()
				.setThemeColor(theme.toThemePreference())
				.build()
		}
	}

	suspend fun setTaskAlign(taskAlign: TaskAlign) = withContext(ioDispatcher) {
		textAlignDataStore.updateData {
			it.toBuilder()
				.setTaskAlign(taskAlign.toTextAlignPreference())
				.build()
		}
	}

	suspend fun setFontSize(fontSize: FontSize) = withContext(ioDispatcher) {
		fontSizeDataStore.updateData {
			it.toBuilder()
				.setFontSize(fontSize.toFontSizePreference())
				.build()
		}
	}

}