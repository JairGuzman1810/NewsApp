package com.app.news.domain.usecases

/**
 * Data class to hold all use cases related to app entry.
 *
 * This class acts as a container for the ReadAppEntry and SaveAppEntry
 * use cases, providing a convenient way to access them together. This is
 * useful for dependency injection and for organizing related use cases.
 *
 * @param readAppEntry The use case for reading the app entry status.
 * @param saveAppEntry The use case for saving the app entry status.
 */
data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)