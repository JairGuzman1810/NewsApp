package com.app.news.domain.usecases.news

import com.app.news.data.local.dao.NewsDao
import com.app.news.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Use case for retrieving all bookmarked articles from the local database.
 *
 * This use case encapsulates the logic for fetching all bookmarked
 * articles from the local database. It uses the NewsDao to perform the
 * database operation and converts the ArticleEntity objects to Article
 * domain models.
 *
 * @property newsDao The NewsDao instance used to interact with the
 *   local database.
 */
class SelectArticles(
    private val newsDao: NewsDao
) {

    /**
     * Invokes the use case to retrieve all bookmarked articles.
     *
     * This method is the primary entry point for executing the use case.
     * It retrieves all articles from the database and converts them to
     * Article domain models.
     *
     * @return A Flow of List<Article> objects, representing the stream
     *   of bookmarked articles.
     */
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getAllArticles().map { articleEntities ->
            articleEntities.map { it.toArticle() }
        }
    }
}