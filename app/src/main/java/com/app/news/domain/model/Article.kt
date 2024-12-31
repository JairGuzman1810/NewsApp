package com.app.news.domain.model

import com.app.news.data.local.entity.ArticleEntity

/**
 * Domain model representing an article.
 *
 * This class is used to represent an article within the domain layer of
 * the application. It contains the essential information about an article.
 *
 * @property author The author of the article.
 * @property content The main content of the article.
 * @property description A short description or summary of the article.
 * @property publishedAt The date and time when the article was published.
 * @property source The source of the article.
 * @property title The title of the article.
 * @property url The URL of the article.
 * @property urlToImage The URL of the image associated with the article.
 */
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

/**
 * Extension function to convert an Article domain model to an ArticleEntity.
 *
 * @return An ArticleEntity representing the data in this Article.
 */
fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = "${source.id},${source.name}",
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}