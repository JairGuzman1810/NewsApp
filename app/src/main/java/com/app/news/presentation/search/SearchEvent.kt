package com.app.news.presentation.search


/**
 * Represents the events that can occur on the search screen.
 *
 * This sealed class defines the possible events that can be triggered by
 * user interactions or other actions on the search screen.
 */
sealed class SearchEvent {

    /**
     * Event to update the search query.
     *
     * @property query The new search query string.
     */
    data class UpdateSearchQuery(val query: String): SearchEvent()

    /**
     * Event to trigger a search for news articles.
     */
    data object SearchNews: SearchEvent()
}