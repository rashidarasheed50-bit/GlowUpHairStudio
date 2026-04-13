package com.glowup.hairstudio.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "glowup_preferences")

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore
    
    companion object {
        val CURRENT_USER_ID = intPreferencesKey(Constants.PREF_CURRENT_USER_ID)
        val IS_FIRST_LAUNCH = booleanPreferencesKey(Constants.PREF_IS_FIRST_LAUNCH)
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey(Constants.PREF_NOTIFICATIONS_ENABLED)
    }
    
    // Current User ID
    suspend fun saveCurrentUserId(userId: Int) {
        dataStore.edit { preferences ->
            preferences[CURRENT_USER_ID] = userId
        }
    }
    
    suspend fun getCurrentUserId(): Int {
        val preferences = dataStore.data.first()
        return preferences[CURRENT_USER_ID] ?: -1
    }
    
    fun getCurrentUserIdFlow(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[CURRENT_USER_ID] ?: -1
        }
    }
    
    suspend fun clearCurrentUserId() {
        dataStore.edit { preferences ->
            preferences.remove(CURRENT_USER_ID)
        }
    }
    
    // First Launch
    suspend fun setFirstLaunchCompleted() {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_LAUNCH] = false
        }
    }
    
    suspend fun isFirstLaunch(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[IS_FIRST_LAUNCH] ?: true
    }
    
    // Notifications
    suspend fun setNotificationsEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_ENABLED] = enabled
        }
    }
    
    suspend fun areNotificationsEnabled(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[NOTIFICATIONS_ENABLED] ?: true
    }
    
    // Clear all preferences
    suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
