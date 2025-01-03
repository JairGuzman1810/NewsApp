package com.app.news.domain.model

import android.os.Parcelable
import com.app.news.data.local.entity.ArticleEntity
import kotlinx.parcelize.Parcelize

/**
 * Domain model representing a news article.
 *
 * This data class is used to represent a news article within the domain layer
 * of the application. It encapsulates the core information of an article,
 * making it suitable for use in business logic and presentation layers.
 *
 * @property author The author of the article.
 * @property content The main content or body of the article.
 * @property description A brief description or summary of the article.
 * @property publishedAt The date and time when the article was published.
 * @property source The source from which the article originated.
 * @property title The title of the article.
 * @property url The unique URL that identifies the article.
 * @property urlToImage The URL of an image associated with the article.
 */
@Parcelize
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable

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