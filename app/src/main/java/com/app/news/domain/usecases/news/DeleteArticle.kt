package com.app.news.domain.usecases.news

import com.app.news.data.local.dao.NewsDao
import com.app.news.domain.model.Article
import com.app.news.domain.model.toArticleEntity

/**
 * Use case for deleting an article from the local database.
 *
 * This use case encapsulates the logic for deleting an article from the
 * local database. It uses the NewsDao to perform the database operation.
 *
 * @property newsDao The NewsDao instance used to interact with the
 *   local database.
 */
class DeleteArticle(
    private val newsDao: NewsDao
) {

    /**
     * Invokes the use case to delete an article from the database.
     *
     * This method is the primary entry point for executing the use case.
     * It converts the Article domain model to an ArticleEntity and then
     * delegates the database operation to the NewsDao.
     *
     * @param article The Article domain model to delete.
     */
    suspend operator fun invoke(article: Article) {
        newsDao.delete(article.toArticleEntity())
    }

}