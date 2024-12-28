package com.app.news.domain.usecases.news

/**
 * Wrapper class for all news-related use cases.
 *
 * This class provides a convenient way to group and access all use cases
 * related to news articles. It allows for easier dependency injection
 * and organization of use cases.
 *
 * @property getNews The GetNews use case instance.
 */
data class NewsUseCases(
    val getNews: GetNews,
)