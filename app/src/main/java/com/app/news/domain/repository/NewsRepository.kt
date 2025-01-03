package com.app.news.domain.repository

import androidx.paging.PagingData
import com.app.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Interface representing the repository for accessing news data.
 *
 * This interface defines the contract for retrieving news articles.
 * Implementations of this interface are responsible for fetching news
 * data from various sources, such as a network API or a local database.
 *
 * The domain layer interacts with this interface to access news data,
 * without needing to know the specifics of how the data is retrieved.
 */
interface NewsRepository {

    /**
     * Fetches a stream of paging data containing news articles.
     *
     * This method retrieves news articles from the specified sources and
     * returns them as a `Flow` of `PagingData<Article>`. The `PagingData`
     * object allows for efficient loading and display of large datasets
     * in a paginated manner.
     *
     * @param sources A list of news sources to fetch articles from.
     *   Each source is represented as a string.
     * @return A `Flow` of `PagingData<Article>` objects, representing the
     *   stream of news articles.
     */
    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    /**
     * Searches for news articles based on a query and a list of sources.
     *
     * This method retrieves news articles that match the provided search query
     * from the specified sources. The results are returned as a `Flow` of
     * `PagingData<Article>`, which allows for efficient loading and display of
     * large datasets in a paginated manner.
     *
     * @param searchQuery The query string to search for in the news articles.
     * @param sources A list of news sources to search within. Each source is
     *   represented as a string.
     * @return A `Flow` of `PagingData<Article>` objects, representing the
     *   stream of news articles that match the search query.
     */
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    /**
     * Inserts or updates an article in the local database.
     *
     * This method saves an article to the local database. If an article with the same URL already
     * exists, it will be replaced.
     *
     * @param article The Article object to insert or update.
     */
    suspend fun upsertArticle(article: Article)

    /**
     * Deletes an article from the local database by its URL.
     *
     * @param url The URL of the article to delete.
     */
    suspend fun deleteArticle(url: String)

    /**
     * Retrieves all articles from the local database.
     *
     * @return A Flow emitting a list of Article objects representing all articles in the local database.
     */
    fun selectArticles(): Flow<List<Article>>

    /**
     * Retrieves a specific article from the local database by its URL.
     *
     * @param url The URL of the article to retrieve.
     * @return The Article object if found, or null if no article with the given URL exists.
     */
    suspend fun selectArticle(url: String): Article?
}