package com.app.news.data.util

/**
 * Object containing constant values used throughout the data layer.
 *
 * This object centralizes the definition of constant strings and values,
 * ensuring consistency and avoiding magic strings. By using this object,
 * we can easily manage and update constants in one place, reducing the
 * risk of errors and improving code maintainability.
 */
object Constants {
    /**
     * The name of the data store used to store user settings.
     *
     * This constant is used as the file name for the data store that holds
     * user-specific preferences. It's used when creating the data store
     * instance.
     */
    const val USER_SETTINGS = "userSettings"

    /**
     * The key used to store and retrieve the app entry status in the data store.
     *
     * This constant is used as the key for the boolean value that indicates
     * whether the user has completed the onboarding process or not. It's
     * used when reading and writing the app entry status to the data store.
     */
    const val APP_ENTRY = "appEntry"

    /**
     * The API key used to authenticate requests to the news API.
     *
     * This constant holds the API key required to access the news API.
     * It's used as a parameter in API requests to identify and authorize
     * the application.
     */
    const val API_KEY = "YOUR_API_KEY"

    /**
     * The base URL for the news API.
     *
     * This constant defines the root URL for all API requests to the news
     * service. It's used to construct the full URL for each API endpoint.
     */
    const val BASE_URL = "https://newsapi.org/v2/"

    /**
     * The name of the news database.
     *
     * This constant is used as the file name for the Room database that holds
     * the news articles. It's used when creating the database instance.
     */
    const val NEWS_DATABASE_NAME = "news_db"
}