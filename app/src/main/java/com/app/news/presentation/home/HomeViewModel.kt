package com.app.news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.app.news.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the Home screen.
 *
 * This ViewModel is responsible for managing the data and business logic
 * for the Home screen. It fetches news articles from the NewsUseCases and
 * exposes them as a stream of PagingData.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    newsUseCases: NewsUseCases
) : ViewModel() {

    /**
     * A stream of news articles.
     *
     * This property holds a Flow of PagingData, which represents a
     * paginated list of news articles. The articles are fetched from the
     * NewsUseCases and cached within the ViewModel's scope.
     *
     * The sources for the news are "bbc-news", "abc-news", and
     * "al-jazeera-english".
     */
    val news = newsUseCases.getNews(
        sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)
}