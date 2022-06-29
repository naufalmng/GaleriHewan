package org.d3if2146.galerihewan.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val LAYOUT_PREFERENCES_NAME = "LAYOUT_PREFERENCE"

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = LAYOUT_PREFERENCES_NAME)

class DataStoreSettings(preferenceDataStore: DataStore<Preferences>){
    companion object {
        private val IS_LINEAR_LAYOUT_MANAGER = booleanPreferencesKey("isLinearLayoutManager")
    }

    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean,ctx: Context){
        ctx.dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT_MANAGER] = isLinearLayoutManager
        }
    }

    val preferenceFlow: Flow<Boolean> = preferenceDataStore.data
        .catch {
            if(it is IOException){
                it.printStackTrace()
                emit(emptyPreferences())
            } else{
                throw it
            }
        }.map { preferences ->
            preferences[IS_LINEAR_LAYOUT_MANAGER] ?: true
        }
}