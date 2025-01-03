package com.app.news.domain.usecases.news

import com.app.news.domain.model.Article
import com.app.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving all bookmarked articles from the local database.
 *
 * This use case encapsulates the logic for fetching all bookmarked
 * articles from the local database. It uses the NewsRepository to perform the
 * database operation and returns a flow of Article domain models.
 *
 * @property newsRepository The repository for interacting with news data.
 */
class SelectArticles(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case to retrieve all bookmarked articles.
     *
     * This method is the primary entry point for executing the use case.
     * It retrieves all articles from the database and returns them as a
     * Flow of List<Article> domain models.
     *
     * @return A Flow emitting a List of Article objects, representing the stream
     *   of bookmarked articles.
     */
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}