package com.app.news.domain.usecases

import com.app.news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

/**
 * Use case for reading the app entry status.
 *
 * This use case is responsible for retrieving the app entry status from the
 * LocalUserManager. The app entry status indicates whether the user has
 * completed the onboarding process or not.
 *
 * @param localUserManager The LocalUserManager instance used to read the app
 *                         entry status.
 */
class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    /**
     * Executes the use case to read the app entry status.
     *
     * @return A Flow of Boolean that emits `true` if the app has been
     *         opened before, `false` otherwise.
     */
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}