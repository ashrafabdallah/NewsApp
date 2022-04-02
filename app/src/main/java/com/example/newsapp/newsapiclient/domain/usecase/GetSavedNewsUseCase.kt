package com.example.newsapp.newsapiclient.domain.usecase

import com.example.newsapp.newsapiclient.data.model.Article
import com.example.newsapp.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun executegetSavedNewsFromDB(): Flow<List<Article>>{
        return newsRepository.getSavedNewsFromDB()
    }
}