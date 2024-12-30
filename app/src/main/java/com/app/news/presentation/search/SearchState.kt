package com.app.news.presentation.search

import androidx.paging.PagingData
import com.app.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Represents the state of the search screen.
 *
 * This data class holds the current state of the search screen, including
 * the search query and the list of articles that match the query.
 *
 * @property searchQuery The current search query entered by the user.
 * @property articles A Flow of PagingData<Article> representing the
 *   paginated list of articles that match the search query. It can be null
 *   if no search has been performed yet or if there are no results.
 */
data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)