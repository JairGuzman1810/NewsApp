package com.app.news.domain.usecases.news

import com.app.news.data.local.dao.NewsDao
import com.app.news.domain.model.Article
import com.app.news.domain.model.toArticleEntity

/**
 * Use case for inserting or updating an article in the local database.
 *
 * This use case encapsulates the logic for saving an article to the
 * local database. It uses the NewsDao to perform the database operation.
 *
 * @property newsDao The NewsDao instance used to interact with the
 *   local database.
 */
class UpsertArticle(
    private val newsDao: NewsDao
) {

    /**
     * Invokes the use case to insert or update an article in the database.
     *
     * This method is the primary entry point for executing the use case.
     * It converts the Article domain model to an ArticleEntity and then
     * delegates the database operation to the NewsDao.
     *
     * @param article The Article domain model to insert or update.
     */
    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article.toArticleEntity())
    }

}