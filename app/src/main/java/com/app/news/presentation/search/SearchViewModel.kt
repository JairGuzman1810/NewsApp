package com.app.news.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.app.news.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the search screen.
 *
 * This ViewModel is responsible for managing the state and handling events
 * on the search screen. It interacts with the NewsUseCases to perform
 * searches for news articles.
 *
 * @property newsUseCases The NewsUseCases instance used to interact with
 *   the domain layer for news-related operations.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    /**
     * The current state of the search screen.
     *
     * This is a mutable state that holds the current SearchState.
     */
    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    /**
     * Handles events triggered on the search screen.
     *
     * This method is called when an event occurs on the search screen. It
     * updates the state of the ViewModel based on the event.
     *
     * @param event The SearchEvent that occurred.
     */
    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.UpdateSearchQuery -> {
                // Update the search query in the state.
                _state.value = state.value.copy(searchQuery = event.query)
            }
            is SearchEvent.SearchNews -> {
                // Trigger a search for news articles.
                searchNews()
            }
        }
    }

    /**
     * Searches for news articles based on the current search query.
     *
     * This method uses the NewsUseCases to perform a search for news
     * articles. The results are cached in the ViewModel's scope and then
     * used to update the state.
     */
    private fun searchNews() {
        // Perform the search using the NewsUseCases.
        val articles = newsUseCases.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english") // Specify the sources to search within.
        ).cachedIn(viewModelScope) // Cache the results in the ViewModel's scope.

        // Update the state with the search results.
        _state.value = state.value.copy(articles = articles)
    }
}