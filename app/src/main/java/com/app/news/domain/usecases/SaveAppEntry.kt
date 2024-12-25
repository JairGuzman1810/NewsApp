package com.app.news.domain.usecases

import com.app.news.domain.manager.LocalUserManager

/**
 * Use case for saving the app entry status.
 *
 * This use case is responsible for saving the app entry status using the
 * LocalUserManager. The app entry status indicates that the user has
 * opened the app at least once, which is typically done after the user has
 * completed the onboarding process.
 *
 * @param localUserManager The LocalUserManager instance used to save the app
 *                         entry status.
 */
class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    /**
     * Executes the use case to save the app entry status.
     *
     * This method calls the LocalUserManager.saveAppEntry method to persist
     * the app entry status.
     */
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}