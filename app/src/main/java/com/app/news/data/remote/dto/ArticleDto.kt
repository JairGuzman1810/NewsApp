package com.app.news.data.remote.dto

/**
 * Data Transfer Object (DTO) representing an article from the news API.
 *
 * This class is used to receive article data from the remote API. It
 * mirrors the structure of the JSON response from the API.
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
data class ArticleDto(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String
)