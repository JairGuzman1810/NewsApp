package com.app.news.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.news.data.remote.dto.ArticleDto

/**
 * A custom PagingSource implementation for fetching news articles.
 *
 * This class is responsible for loading pages of news articles from the
 * network using the NewsApi. It handles the logic for determining the
 * next and previous keys for pagination and for mapping the network
 * response to a list of ArticleDto objects.
 *
 * @property newsApi The NewsApi instance used to make network requests.
 * @property sources A comma-separated string of news sources to fetch
 *   articles from.
 */
class NewPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, ArticleDto>() {

    /**
     * Keeps track of the total number of news articles fetched so far.
     *
     * This variable is used to determine when all available articles have
     * been loaded.
     */
    private var totalNewsCount = 0

    /**
     * Loads a page of news articles.
     *
     * This method is called by the Paging library to load a new page of
     * data. It makes a network request to the NewsApi to fetch the
     * articles and then returns a LoadResult indicating the success or
     * failure of the operation.
     *
     * @param params The LoadParams object containing information about
     *   the load operation, such as the requested page key.
     * @return A LoadResult object indicating the success or failure of
     *   the load operation. If successful, it contains a LoadResult.Page
     *   with the loaded data and the next and previous keys.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        // Determine the page number to load.
        val page = params.key ?: 1
        return try {
            // Make a network request to fetch the news articles.
            val newsResponse = newsApi.getNews(sources = sources, page = page)

            // Update the total news count.
            totalNewsCount += newsResponse.articles.size

            // Remove duplicate articles based on their title.
            val articles = newsResponse.articles.distinctBy { it.title }

            // Return a successful LoadResult.Page.
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            // Handle any exceptions that occur during the network request.
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    /**
     * Determines the refresh key for the Paging library.
     *
     * This method is called when the Paging library needs to refresh the
     * data. It calculates the appropriate refresh key based on the current
     * state.
     *
     * @param state The current [PagingState] object.
     * @return The refresh key, or null if no refresh is needed.
     */
    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        // Calculate the refresh key based on the anchor position.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}