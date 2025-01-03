package com.app.news.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.news.domain.model.Article
import com.app.news.domain.model.Source

/**
 * Represents an article record in the "article" database table.
 *
 * This entity stores the source information as a comma-separated string.
 */
@Entity(tableName = "article")
data class ArticleEntity(
    /**
     * Unique ID for the article (auto-generated by Room). Primary key.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "articleId") val articleId: Int = 0,

    /**
     * The author of the article. Can be null.
     */
    @ColumnInfo(name = "author") val author: String?,

    /**
     * The main content of the article. Can be null.
     */
    @ColumnInfo(name = "content") val content: String?,

    /**
     * A short description or summary of the article. Can be null.
     */
    @ColumnInfo(name = "description") val description: String?,

    /**
     * The date and time when the article was published. Can be null.
     */
    @ColumnInfo(name = "publishedAt") val publishedAt: String?,

    /**
     * The source of the article, stored as a comma-separated string (id,name).
     */
    @ColumnInfo(name = "source") val source: String,

    /**
     * The title of the article. Can be null.
     */
    @ColumnInfo(name = "title") val title: String?,

    /**
     * The URL of the article. Can be null.
     */
    @ColumnInfo(name = "url") val url: String?,

    /**
     * The URL of the image associated with the article. Can be null.
     */
    @ColumnInfo(name = "urlToImage") val urlToImage: String?
) {
    /**
     * Converts this ArticleEntity to an Article domain model.
     *
     * @return An Article domain model representing the data in this entity.
     */
    fun toArticle(): Article {
        val sourceParts = source.split(",")
        val source = if (sourceParts.size == 2) {
            Source(id = sourceParts[0], name = sourceParts[1])
        } else {
            Source(id = "", name = "") // Handle invalid format
        }
        return Article(
            author = author ?: "",
            content = content ?: "",
            description = description ?: "",
            publishedAt = publishedAt ?: "",
            source = source,
            title = title ?: "",
            url = url ?: "",
            urlToImage = urlToImage ?: ""
        )
    }
}