package com.app.news.data.util

/**
 * Object containing constant values used throughout the data layer.
 *
 * This object centralizes the definition of constant strings and values,
 * ensuring consistency and avoiding magic strings.
 */
object Constants {
    /**
     * The name of the data store used to store user settings.
     *
     * This constant is used as the file name for the data store that holds
     * user-specific preferences.
     */
    const val USER_SETTINGS = "userSettings"

    /**
     * The key used to store and retrieve the app entry status in the data store.
     *
     * This constant is used as the key for the boolean value that indicates
     * whether the user has completed the onboarding process or not.
     */
    const val APP_ENTRY = "appEntry"
}