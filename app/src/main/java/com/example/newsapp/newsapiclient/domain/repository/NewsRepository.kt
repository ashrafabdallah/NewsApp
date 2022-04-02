package com.example.newsapp.newsapiclient.domain.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.newsapiclient.data.model.ApiResponse
import com.example.newsapp.newsapiclient.data.model.Article
import com.example.newsapp.newsapiclient.data.util.Resource
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    suspend fun getNewsHeadLines(
        country: String,
        page: Int,
    ): Resource<ApiResponse>

    suspend fun getSearchNews(
        country: String,
        query: String,
        page: Int
    ): Resource<ApiResponse>

    suspend fun saveNewInDB(article: Article)
    suspend fun deleteSavedNewsInDB(article: Article)
    fun getSavedNewsFromDB(): Flow<List<Article>>

}