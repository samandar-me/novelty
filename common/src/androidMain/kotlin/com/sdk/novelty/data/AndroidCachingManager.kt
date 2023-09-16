package com.sdk.novelty.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

actual class CachingManager(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore("theme_cache")

    private val key = intPreferencesKey("index_key")
    actual suspend fun saveThemeIndex(index: Int) {
        context.dataStore.edit {
            it[key] = index
        }
    }

    actual fun getThemeIndex(): Flow<Int> = context.dataStore.data.map {
        it[key] ?: 0
    }
}