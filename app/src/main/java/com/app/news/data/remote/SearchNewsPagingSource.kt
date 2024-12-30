package com.app.news.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.news.data.remote.dto.ArticleDto

/**
 * A custom PagingSource implementation for fetching search news articles.
 *
 * This class is responsible for loading pages of news articles from the
 * network using the NewsApi based on a search query. It handles the logic
 * for determining the next and previous keys for pagination, for making
 * network requests, and for mapping the network response to a list of
 * ArticleDto objects.
 *
 * @property newsApi The NewsApi instance used to make network requests.
 * @property searchQuery The query string to search for in the news articles.
 * @property sources A comma-separated string of news sources to fetch
 *   articles from.
 */
class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String
) : PagingSource<Int, ArticleDto>() {

    /**
     * Keeps track of the total number of news articles fetched so far.
     *
     * This variable is used to determine when all available articles have
     * been loaded, which is used to decide whether there are more pages to
     * load.
     */
    private var totalNewsCount = 0

    /**
     * Loads a page of news articles based on the search query.
     *
     * This method is called by the Paging library to load a new page of
     * data. It makes a network request to the NewsApi to fetch the
     * articles that match the search query and then returns a LoadResult
     * indicating the success or failure of the operation.
     *
     * @param params The LoadParams object containing information about
     *   the load operation, such as the requested page key.
     * @return A LoadResult object indicating the success or failure of
     *   the load operation. If successful, it contains a LoadResult.Page
     *   with the loaded data and the next and previous keys.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        // Determine the page number to load. If params.key is null, it's the first page, so we load page 1.
        val page = params.key ?: 1
        return try {
            // Make a network request to fetch the news articles based on the search query.
            val newsResponse =
                newsApi.searchNews(searchQuery = searchQuery, sources = sources, page = page)

            // Update the total news count with the number of articles fetched in this response.
            totalNewsCount += newsResponse.articles.size

            // Remove duplicate articles based on their title to ensure uniqueness in the list.
            val articles = newsResponse.articles.distinctBy { it.title }

            // Return a successful LoadResult.Page with the loaded articles.
            LoadResult.Page(
                data = articles,
                // Determine the next key. If totalNewsCount equals totalResults, there are no more pages.
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null // For simplicity, we don't support loading previous pages in this example.
            )
        } catch (e: Exception) {
            // Handle any exceptions that occur during the network request.
            e.printStackTrace()
            // Return a LoadResult.Error to indicate that the load operation failed.
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
     * @param state The current PagingState object.
     * @return The refresh key, or null if no refresh is needed.
     */
    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        // Calculate the refresh key based on the anchor position.
        return state.anchorPosition?.let { anchorPosition ->
            // Find the page that contains the anchor position.
            val anchorPage = state.closestPageToPosition(anchorPosition)
            // If there's a previous page, use the next key of the previous page as the refresh key.
            // Otherwise, if there's a next page, use the previous key of the next page as the refresh key.
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
