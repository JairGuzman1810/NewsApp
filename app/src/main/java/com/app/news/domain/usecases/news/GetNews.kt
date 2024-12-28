package com.app.news.domain.usecases.news

import androidx.paging.PagingData
import com.app.news.domain.model.Article
import com.app.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving news articles from the repository.
 *
 * This use case encapsulates the logic for fetching news articles from
 * the NewsRepository. It takes a list of sources as input and returns
 * a Flow of PagingData<Article>, allowing for efficient loading and
 * display of large datasets in a paginated manner.
 *
 * @property newsRepository The NewsRepository instance used to fetch
 *   news articles.
 */
class GetNews(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case to retrieve news articles from the specified sources.
     *
     * This method is the primary entry point for executing the use case.
     * It delegates the actual fetching of news articles to the
     * NewsRepository.
     *
     * @param sources A list of news sources to fetch articles from. Each
     *   source is represented as a string (e.g., "bbc-news").
     * @return A Flow of PagingData<Article> objects, representing the
     *   stream of news articles.
     */
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}