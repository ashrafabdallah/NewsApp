package com.example.newsapp.newsapiclient.data.repostory.dataresourceimpl

import com.example.newsapp.newsapiclient.data.api.NewsApi
import com.example.newsapp.newsapiclient.data.model.ApiResponse
import com.example.newsapp.newsapiclient.data.repostory.dataresource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceIMPL(
    private val newsApi: NewsApi,
) : RemoteDataSource {
    override suspend fun getTopNewsHeadLines(
        country: String,
        page: Int,
    ): Response<ApiResponse> {
        return newsApi.getTopNewsHeadLines(country, page)
    }

    override suspend fun getSearchTopNewsHeadLines(
        country: String,
        query: String,
        page: Int
    ): Response<ApiResponse> {
        return newsApi.getSearchTopNewsHeadLines(country,query,page)
    }
}