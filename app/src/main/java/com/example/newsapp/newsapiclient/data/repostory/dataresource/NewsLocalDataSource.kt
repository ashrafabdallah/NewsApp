package com.example.newsapp.newsapiclient.data.repostory.dataresource

import com.example.newsapp.newsapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArtical(article: Article)
    fun getSavedNewsFromDB(): Flow<List<Article>>
    suspend fun deleteNewsFromDb(article: Article)
}