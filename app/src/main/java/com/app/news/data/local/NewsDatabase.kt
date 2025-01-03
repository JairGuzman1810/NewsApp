package com.app.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.news.data.local.dao.NewsDao
import com.app.news.data.local.entity.ArticleEntity

/**
 * The Room database for the news application.
 *
 * This class defines the database configuration and provides access to the DAOs.
 * It includes both ArticleEntity and SourceEntity as entities in the database.
 */
@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    /**
     * Provides access to the NewsDao for interacting with the article table.
     *
     * @return The NewsDao instance.
     */
    abstract fun newsDao(): NewsDao
}