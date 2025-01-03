package com.app.news.domain.usecases.news

import com.app.news.domain.repository.NewsRepository

/**
 * Use case for deleting an article from the local database.
 *
 * This use case encapsulates the logic for deleting an article from the
 * local database. It uses the NewsRepository to perform the database operation.
 *
 * @property newsRepository The NewsRepository instance used to interact with the
 *   local database.
 */
class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case to delete an article from the local database.
     *
     * This method is the primary entry point for executing the use case.
     * It uses the article's URL to identify and delete the article.
     *
     * @param url The URL of the article to delete.
     */
    suspend operator fun invoke(url: String) {
        newsRepository.deleteArticle(url)
    }
}