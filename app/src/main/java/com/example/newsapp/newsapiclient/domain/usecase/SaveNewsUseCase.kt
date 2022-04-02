package com.example.newsapp.newsapiclient.domain.usecase

import com.example.newsapp.newsapiclient.data.model.Article
import com.example.newsapp.newsapiclient.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun executeSaveNewInDB(article: Article){
        newsRepository.saveNewInDB(article)
    }
}