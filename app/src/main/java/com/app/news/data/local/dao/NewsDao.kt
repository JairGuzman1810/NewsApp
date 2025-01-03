package com.app.news.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.news.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for interacting with the "article" table in the database.
 *
 * This interface defines the methods for performing CRUD (Create, Read, Update, Delete)
 * operations on ArticleEntity objects.
 */
@Dao
interface NewsDao {

    /**
     * Inserts or updates an article in the database.
     *
     * If an article with the same primary key (id) already exists, it will be replaced.
     *
     * @param articleEntity The ArticleEntity to insert or update.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articleEntity: ArticleEntity)


    /**
     * Deletes an article from the database by its URL.
     *
     * @param url The URL of the article to delete.
     */
    @Query("DELETE FROM article WHERE url=:url")
    suspend fun delete(url: String)

    /**
     * Retrieves all articles from the database.
     *
     * @return A list of ArticleEntity objects representing all articles in the database.
     */
    @Query("SELECT * FROM article")
    fun getAllArticles(): Flow<List<ArticleEntity>>


    /**
     * Retrieves a specific article from the database by its URL.
     *
     * @param url The URL of the article to retrieve.
     * @return The ArticleEntity if found, or null if no article with the given URL exists.
     */
    @Query("SELECT * FROM article WHERE url=:url")
    suspend fun getArticle(url: String): ArticleEntity?
}