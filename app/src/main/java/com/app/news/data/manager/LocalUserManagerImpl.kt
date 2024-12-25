package com.app.news.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.app.news.data.util.Constants
import com.app.news.data.util.Constants.USER_SETTINGS
import com.app.news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of the LocalUserManager interface using DataStore.
 *
 * This class handles the saving and reading of user preferences related to
 * the app's entry status, such as whether the user has completed the
 * onboarding process.
 *
 * @param context The application context.
 */
class LocalUserManagerImpl(
    private val context: Context,
) : LocalUserManager {

    /**
     * Saves the app entry status to DataStore.
     *
     * This method sets the PreferencesKey.APP_ENTRY key to `true`,
     * indicating that the user has opened the app at least once.
     */
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKey.APP_ENTRY] = true
        }
    }

    /**
     * Reads the app entry status from DataStore.
     *
     * @return A Flow of Boolean that emits `true` if the app has been
     *         opened before, `false` otherwise. If the key is not found, it
     *         defaults to `false`.
     */
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKey.APP_ENTRY] ?: false
        }
    }
}

/**
 * Extension property to create a DataStore instance for the given context.
 *
 * This DataStore is used to store user preferences and is named using the
 * USER_SETTINGS constant.
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

/**
 * Object containing the keys used to access preferences in DataStore.
 *
 * This object centralizes the definition of preference keys, ensuring
 * consistency and avoiding magic strings.
 */
private object PreferencesKey {
    /**
     * The key used to store and retrieve the app entry status.
     *
     * This key is associated with a boolean value that indicates whether the
     * user has completed the onboarding process or not.
     */
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}