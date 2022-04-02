package com.example.newsapp.newsapiclient.domain.usecase

import com.example.newsapp.newsapiclient.data.model.ApiResponse
import com.example.newsapp.newsapiclient.data.util.Resource
import com.example.newsapp.newsapiclient.domain.repository.NewsRepository

class GetSearchNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun executeGetSearchNews(country: String,
                                     query: String,
                                     page: Int): Resource<ApiResponse>{
        return newsRepository.getSearchNews(country,query,page)
    }
}