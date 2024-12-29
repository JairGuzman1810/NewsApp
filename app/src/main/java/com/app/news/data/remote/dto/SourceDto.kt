package com.app.news.data.remote.dto

/**
 * Data Transfer Object (DTO) representing the source of an article.
 *
 * This class is used to receive source data from the news API. It
 * mirrors the structure of the JSON response for the source of an
 * article.
 *
 * @property id The ID of the source.
 * @property name The name of the source.
 */
data class SourceDto(
    val id: String?,
    val name: String?
)