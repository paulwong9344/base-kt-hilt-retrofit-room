package com.example.paulsample.repository.db.news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paulsample.repository.model.news.News

/**
 * Abstracts access to the news database
 */
@Dao
interface NewsDao {

    /**
     * Insert articles into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<News>): List<Long>

    /**
     * Get all the articles from database
     */
    @Query("SELECT * FROM news_table")
    fun getNewsArticles(): LiveData<List<News>>

    @Query("DELETE FROM news_table")
    fun deleteAllArticles()
}