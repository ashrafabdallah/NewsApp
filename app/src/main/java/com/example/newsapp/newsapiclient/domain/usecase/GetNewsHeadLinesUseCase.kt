package com.example.newsapp.newsapiclient.domain.usecase

import com.example.newsapp.newsapiclient.data.model.ApiResponse
import com.example.newsapp.newsapiclient.data.util.Resource
import com.example.newsapp.newsapiclient.domain.repository.NewsRepository

class GetNewsHeadLinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun executeGetNewsHeadLines(
        country: String,
        page: Int,
    ): Resource<ApiResponse> {
        return newsRepository.getNewsHeadLines(country, page)
    }
}