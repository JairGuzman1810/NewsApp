package com.app.news.domain.model

/**
 * Domain model representing the source of an article.
 *
 * This class is used to represent the source of an article within the
 * domain layer of the application.
 *
 * @property id The ID of the source.
 * @property name The name of the source.
 */
data class Source(
    val id: String,
    val name: String
)