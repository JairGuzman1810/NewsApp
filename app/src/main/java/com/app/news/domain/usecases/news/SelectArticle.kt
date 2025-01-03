package com.app.news.domain.usecases.news

import com.app.news.domain.model.Article
import com.app.news.domain.repository.NewsRepository

/**
 * Use case for selecting a specific article from the local database by its URL.
 *
 * This use case retrieves an article from the local database based on the provided URL.
 * It returns the article directly, or null if not found.
 *
 * @property newsRepository The repository for interacting with news data.
 */
class SelectArticle (
    private val newsRepository: NewsRepository
) {

    /**
     * Executes the use case to retrieve an article by its URL.
     *
     * @param url The URL of the article to retrieve.
     * @return The Article if found, or null if not found.
     */
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}