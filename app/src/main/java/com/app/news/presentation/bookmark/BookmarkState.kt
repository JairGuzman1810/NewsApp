package com.app.news.presentation.bookmark

import com.app.news.domain.model.Article

/**
 * Data class to represent the state of the Bookmark screen.
 *
 * This class holds the list of bookmarked articles that will be displayed
 * on the Bookmark screen.
 *
 * @param articles The list of bookmarked articles.
 */
data class BookmarkState(
    val articles: List<Article> = emptyList()
)
