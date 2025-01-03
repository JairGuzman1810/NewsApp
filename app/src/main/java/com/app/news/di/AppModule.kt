package com.app.news.di

import android.app.Application
import androidx.room.Room
import com.app.news.data.local.NewsDatabase
import com.app.news.data.local.dao.NewsDao
import com.app.news.data.manager.LocalUserManagerImpl
import com.app.news.data.remote.NewsApi
import com.app.news.data.repository.NewsRepositoryImpl
import com.app.news.data.util.Constants.BASE_URL
import com.app.news.data.util.Constants.NEWS_DATABASE_NAME
import com.app.news.domain.manager.LocalUserManager
import com.app.news.domain.repository.NewsRepository
import com.app.news.domain.usecases.app_entry.AppEntryUseCases
import com.app.news.domain.usecases.app_entry.ReadAppEntry
import com.app.news.domain.usecases.app_entry.SaveAppEntry
import com.app.news.domain.usecases.news.DeleteArticle
import com.app.news.domain.usecases.news.GetNews
import com.app.news.domain.usecases.news.NewsUseCases
import com.app.news.domain.usecases.news.SearchNews
import com.app.news.domain.usecases.news.SelectArticle
import com.app.news.domain.usecases.news.SelectArticles
import com.app.news.domain.usecases.news.UpsertArticle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt module for providing application-level dependencies.
 *
 * This module is responsible for providing instances of classes that should
 * live as long as the application does, such as the LocalUserManager and
 * the AppEntryUseCases.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of LocalUserManager.
     *
     * This method creates and returns a LocalUserManagerImpl instance,
     * which is a singleton and will be the same instance throughout the
     * application's lifecycle.
     *
     * @param application The application instance.
     * @return A LocalUserManager instance.
     */
    @Singleton
    @Provides
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    /**
     * Provides an instance of AppEntryUseCases.
     *
     * This method creates and returns an AppEntryUseCases instance, which
     * contains the ReadAppEntry and SaveAppEntry use cases. It depends on
     * a LocalUserManager instance, which is also provided by this module.
     *
     * @param localUserManager The LocalUserManager instance.
     * @return An AppEntryUseCases instance.
     */
    @Singleton
    @Provides
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    /**
     * Provides an instance of NewsApi.
     *
     * This method creates and returns a NewsApi instance. This instance is
     * used to make network requests to the news API. It uses Retrofit to
     * handle the network communication and Gson to parse the JSON
     * responses.
     *
     * @return A NewsApi instance.
     */
    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    /**
     * Provides an instance of NewsRepository.
     *
     * This method creates and returns a NewsRepositoryImpl instance. This
     * instance is responsible for fetching news articles from the network and the local database.
     * It depends on a NewsApi instance for network operations and a NewsDao instance for local database operations,
     * both of which are also provided by this module.
     *
     * @param newsApi The NewsApi instance for network operations.
     * @param newsDao The NewsDao instance for local database operations.
     * @return A NewsRepository instance.
     */
    @Singleton
    @Provides
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    /**
     * Provides an instance of NewsUseCases.
     *
     * This method creates and returns a NewsUseCases instance. This
     * instance is a wrapper for all news-related use cases, such as
     * GetNews, SearchNews, UpsertArticle, DeleteArticle, SelectArticles, and SelectArticle.
     * It depends on a NewsRepository instance, which is also provided by this module.
     * These use cases are responsible for interacting with the domain layer to perform
     * news-related operations, including network and local database interactions.
     *
     * @param newsRepository The NewsRepository instance.
     * @return A NewsUseCases instance.
     */
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases = NewsUseCases(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository),
        upsertArticle = UpsertArticle(newsRepository),
        deleteArticle = DeleteArticle(newsRepository),
        selectArticles = SelectArticles(newsRepository),
        selectArticle = SelectArticle(newsRepository)
    )

    /**
     * Provides an instance of NewsDatabase.
     *
     * This method creates and returns a NewsDatabase instance. This instance
     * is responsible for managing the local database for news articles.
     * It uses Room to handle the database operations.
     *
     * @param application The application instance.
     * @return A NewsDatabase instance.
     */
    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provides an instance of NewsDao.
     *
     * This method retrieves the NewsDao from the NewsDatabase.
     *
     * @param newsDatabase The NewsDatabase instance.
     * @return A NewsDao instance.
     */
    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao()
}