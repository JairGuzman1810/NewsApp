package com.app.news.data.remote.dto

/**
 * Data Transfer Object (DTO) representing the response from the news API.
 *
 * This class is used to receive the overall response from the news API,
 * which includes a list of articles, the status of the request, and the
 * total number of results.
 *
 * @property articles A list of ArticleDto objects.
 * @property status The status of the API request (e.g., "ok").
 * @property totalResults The total number of articles available.
 */
data class NewsResponse(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)