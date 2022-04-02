package com.example.newsapp.newsapiclient.data.db

import androidx.room.*
import com.example.newsapp.newsapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtical(arical: Article)

    @Query("SELECT * FROM news_table")
    fun getSavedArticale(): Flow<List<Article>>


    @Delete
    suspend fun deleteArtical(arical: Article)
}