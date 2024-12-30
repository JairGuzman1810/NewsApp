package com.app.news.domain.usecases.news

import androidx.paging.PagingData
import com.app.news.domain.model.Article
import com.app.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for searching news articles based on a query.
 *
 * This use case encapsulates the logic for searching news articles from
 * the NewsRepository. It takes a search query and a list of sources as
 * input and returns a Flow of PagingData<Article>, allowing for efficient
 * loading and display of large datasets in a paginated manner.
 *
 * @property newsRepository The NewsRepository instance used to search for
 *   news articles.
 */
class SearchNews(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case to search for news articles based on the query and sources.
     *
     * This method is the primary entry point for executing the use case.
     * It delegates the actual searching of news articles to the
     * NewsRepository.
     *
     * @param searchQuery The query string to search for in the news articles.
     * @param sources A list of news sources to search within. Each source is
     *   represented as a string (e.g., "bbc-news").
     * @return A Flow of PagingData<Article> objects, representing the
     *   stream of news articles that match the search query.
     */
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }

}