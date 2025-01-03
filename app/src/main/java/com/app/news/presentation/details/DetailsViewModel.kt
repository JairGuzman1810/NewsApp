package com.app.news.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.domain.model.Article
import com.app.news.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the details screen.
 *
 * This ViewModel manages the state and logic for the details screen,
 * which displays information about a specific news article. It handles
 * saving and deleting articles, as well as managing side effects like
 * displaying toast messages.
 *
 * @property newsUseCases The use cases for interacting with news articles.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    /**
     * A side effect message to be displayed to the user.
     *
     * This is typically used for displaying toast messages, such as
     * "Article Saved" or "Article Deleted". It is set by the ViewModel
     * and observed by the UI.
     */
    var sideEffect by mutableStateOf<String?>(null)
        private set

    /**
     * Handles events triggered from the UI.
     *
     * This function is the main entry point for events from the details
     * screen. It determines the appropriate action to take based on the
     * received event.
     *
     * @param event The DetailsEvent triggered from the UI.
     */
    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    // Check if the article is already in the database
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null) {
                        // Article is not in the database, so save it
                        upsertArticle(event.article)
                    } else {
                        // Article is in the database, so delete it
                        deleteArticle(event.article)
                    }
                }
            }

            DetailsEvent.RemoveSideEffect -> {
                // Clear the side effect message
                sideEffect = null
            }
        }
    }

    /**
     * Saves an article to the local database.
     *
     * This function uses the NewsUseCases to save the provided article
     * and sets the side effect message to "Article Saved".
     *
     * @param article The Article object to save.
     */
    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article)
        sideEffect = "Article Saved"
    }

    /**
     * Deletes an article from the local database.
     *
     * This function uses the NewsUseCases to delete the provided article
     * and sets the side effect message to "Article Deleted".
     *
     * @param article The Article object to delete.
     */
    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article.url)
        sideEffect = "Article Deleted"
    }
}