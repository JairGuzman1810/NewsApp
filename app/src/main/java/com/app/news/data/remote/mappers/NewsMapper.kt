package com.app.news.data.remote.mappers

import com.app.news.data.remote.dto.ArticleDto
import com.app.news.data.remote.dto.SourceDto
import com.app.news.domain.model.Article
import com.app.news.domain.model.Source

/**
 * Extension function to map an ArticleDto to an Article.
 *
 * This function converts an ArticleDto from the data layer to an
 * Article in the domain layer. It handles nullable fields by providing
 * default values when a field is null.
 *
 * @receiver The ArticleDto to be mapped.
 * @return The corresponding Article object.
 */
fun ArticleDto.toArticle(): Article {
    return Article(
        author = author ?: "", // Provide a default value if null
        content = content ?: "", // Provide a default value if null
        description = description ?: "", // Provide a default value if null
        publishedAt = publishedAt ?: "", // Provide a default value if null
        source = source?.toSource() ?: Source(id = "", name = ""), // Map SourceDto to Source or provide a default Source
        title = title, // Provide a default value if null
        url = url ?: "", // Provide a default value if null
        urlToImage = urlToImage ?: "" // Provide a default value if null
    )
}

/**
 * Extension function to map a SourceDto to a Source.
 *
 * This function converts a SourceDto from the data layer to a Source
 * in the domain layer. It handles nullable fields by providing
 * default values when a field is null.
 *
 * @receiver The SourceDto to be mapped.
 * @return The corresponding Source object.
 */
fun SourceDto?.toSource(): Source {
    return Source(
        id = this?.id ?: "", // Provide a default value if null
        name = this?.name ?: "" // Provide a default value if null
    )
}