package com.app.news.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
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
     * Deletes all articles from the database.
     *
     * This method removes all rows from the "article" table.
     */
    @Delete
    suspend fun delete(articleEntity: ArticleEntity)

    /**
     * Retrieves all articles from the database.
     *
     * @return A list of ArticleEntity objects representing all articles in the database.
     */
    @Query("SELECT * FROM article")
    fun getAllArticles(): Flow<List<ArticleEntity>>
}