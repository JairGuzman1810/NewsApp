package com.app.news.domain.usecases.news

/**
 * Wrapper class for all news-related use cases.
 *
 * This class provides a convenient way to group and access all use cases
 * related to news articles. It allows for easier dependency injection
 * and organization of use cases.
 *
 * @property getNews The use case for retrieving paginated news articles from the network.
 * @property searchNews The use case for searching news articles from the network.
 * @property upsertArticle The use case for inserting or updating a bookmarked article in the local database.
 * @property deleteArticle The use case for deleting a bookmarked article from the local database.
 * @property selectArticles The use case for retrieving all bookmarked articles from the local database.
 * @property selectArticle The use case for retrieving a specific bookmarked article from the local database.
 */
data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle
)