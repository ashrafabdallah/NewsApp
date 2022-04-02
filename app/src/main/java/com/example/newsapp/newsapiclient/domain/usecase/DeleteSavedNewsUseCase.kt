package com.example.newsapp.newsapiclient.domain.usecase

import com.example.newsapp.newsapiclient.data.model.Article
import com.example.newsapp.newsapiclient.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun executeDeleteSaveNewFromDB(article: Article){
        newsRepository.deleteSavedNewsInDB(article)
    }
}