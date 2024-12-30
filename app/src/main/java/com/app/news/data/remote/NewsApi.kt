package com.app.news.data.remote

import com.app.news.data.remote.dto.NewsResponse
import com.app.news.data.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining the API endpoints for fetching news data.
 *
 * This interface uses Retrofit annotations to define how to interact with
 * the remote news API. Each method corresponds to a specific API endpoint
 * and defines the parameters and expected response.
 */
interface NewsApi {

    /**
     * Fetches news articles from the API.
     *
     * This method sends a GET request to the news API to retrieve a list
     * of news articles based on the provided parameters.
     *
     * @param page The page number of the results to retrieve.
     * @param sources The sources from which to retrieve news (e.g., "bbc-news").
     * @param apiKey The API key for authentication. Defaults to the constant
     *   value defined in Constants.API_KEY.
     * @return A NewsResponse object containing the list of articles and
     *   other response details.
     */
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    /**
     * Searches for news articles based on a query.
     *
     * This method sends a GET request to the "everything" endpoint of the news API
     * to search for news articles that match the provided search query.
     *
     * @param searchQuery The query string to search for in the news articles.
     * @param page The page number of the results to retrieve. This is used for pagination.
     * @param sources The sources from which to retrieve news (e.g., "bbc-news").
     *   Multiple sources can be specified, separated by commas.
     * @param apiKey The API key for authentication. Defaults to the constant
     *   value defined in Constants.API_KEY, allowing for easy configuration.
     * @return A NewsResponse object containing the list of articles that match the
     *   search query and other response details, such as the status of the request.
     */
    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}