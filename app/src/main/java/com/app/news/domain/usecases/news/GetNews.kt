package com.app.news.domain.usecases.news

import androidx.paging.PagingData
import com.app.news.domain.model.Article
import com.app.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}