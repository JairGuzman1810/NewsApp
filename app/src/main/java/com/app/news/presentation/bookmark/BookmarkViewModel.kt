package com.app.news.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel for the Bookmark screen.
 *
 * This ViewModel is responsible for managing the data and business logic
 * for the Bookmark screen. It fetches the list of bookmarked articles
 * from the NewsUseCases and exposes them as a state to the UI.
 *
 * @param newsUseCases The use cases for interacting with news data,
 *   specifically for retrieving bookmarked articles.
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    // Private mutable state to hold the BookmarkState.
    private val _state = mutableStateOf(BookmarkState())

    // Public read-only state exposed to the UI.
    val state: State<BookmarkState> = _state

    /**
     * Initializes the ViewModel and fetches the bookmarked articles.
     */
    init {
        getArticles()
    }

    /**
     * Fetches the list of bookmarked articles from the NewsUseCases.
     *
     * This function uses the selectArticles use case to retrieve the
     * bookmarked articles and updates the state accordingly.
     */
    private fun getArticles() {
        newsUseCases.selectArticles().onEach {
            // Update the state with the new list of articles.
            _state.value = _state.value.copy(articles = it.asReversed())
        }.launchIn(viewModelScope) // Launch the coroutine in the ViewModel's scope.
    }
}