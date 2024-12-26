package com.app.news.data.remote.mappers

import com.app.news.data.remote.dto.ArticleDto
import com.app.news.data.remote.dto.SourceDto
import com.app.news.domain.model.Article
import com.app.news.domain.model.Source

/**
 * Extension function to map an ArticleDto to an Article.
 *
 * This function converts an ArticleDto from the data layer to an
 * Article in the domain layer.
 *
 * @receiver The ArticleDto to be mapped.
 * @return The corresponding Article object.
 */
fun ArticleDto.toArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.toSource(), // Map SourceDto to Source
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

/**
 * Extension function to map a SourceDto to a Source.
 *
 * This function converts a SourceDto from the data layer to a Source
 * in the domain layer.
 *
 * @receiver The SourceDto to be mapped.
 * @return The corresponding [Source] object.
 */
fun SourceDto.toSource(): Source {
    return Source(
        id = id,
        name = name
    )
}