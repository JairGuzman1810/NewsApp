package com.app.news.domain.usecases.news

import com.app.news.domain.model.Article
import com.app.news.domain.repository.NewsRepository

/**
 * Use case for inserting or updating an article in the local database.
 *
 * This use case encapsulates the logic for saving an article to the
 * local database. It uses the NewsRepository to perform the database operation.
 *
 * @property newsRepository The repository for interacting with news data.
 */
class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case to insert or update an article in the database.
     *
     * This method is the primary entry point for executing the use case.
     * It delegates the database operation to the NewsRepository.
     *
     * @param article The Article domain model to insert or update.
     */
    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }

}