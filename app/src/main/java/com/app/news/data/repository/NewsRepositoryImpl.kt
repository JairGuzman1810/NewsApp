package com.app.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.app.news.data.remote.NewPagingSource
import com.app.news.data.remote.NewsApi
import com.app.news.data.remote.mappers.toArticle
import com.app.news.domain.model.Article
import com.app.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of the NewsRepository interface.
 *
 * This class is responsible for fetching news articles from the network
 * and mapping them to domain models. It uses the NewPagingSource to
 * handle pagination and the NewsApi to make network requests.
 *
 * @property newsApi The NewsApi instance used to make network requests.
 */
class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    /**
     * Retrieves a stream of news articles from the specified sources.
     *
     * This method uses the Paging library to fetch and paginate news
     * articles. It creates a Pager instance with a PagingConfig and a
     * NewPagingSource to handle the data loading. The resulting
     * PagingData is then mapped to domain models using the toArticle
     * mapper function.
     *
     * @param sources A list of news sources to fetch articles from.
     * @return A Flow of PagingData<Article> objects, representing the
     *   stream of news articles.
     */
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toArticle() } // Map ArticleDto to Article here
        }
    }
}
