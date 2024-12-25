package com.app.news.domain.manager

import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing the local user's data, such as whether the app has
 * been opened before.
 *
 * This interface defines methods for saving and reading the app entry status,
 * which can be used to determine whether to show the onboarding screen or not.
 */
interface LocalUserManager {

    /**
     * Saves the app entry status, indicating that the app has been opened at
     * least once.
     *
     * This method should be called after the user has completed the onboarding
     * process or when the app is first launched.
     */
    suspend fun saveAppEntry()

    /**
     * Reads the app entry status.
     *
     * @return A Flow] of Boolean that emits `true` if the app has been
     *         opened before, `false` otherwise.
     */
    fun readAppEntry(): Flow<Boolean>
}