package com.example.newsapp.newsapiclient.data.api


import androidx.core.os.BuildCompat
import com.example.newsapp.BuildConfig

import com.example.newsapp.newsapiclient.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopNewsHeadLines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = "6866e0e96a8a41149e22a84271a004e5"
    ): Response<ApiResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchTopNewsHeadLines(
        @Query("country") country: String,
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = "6866e0e96a8a41149e22a84271a004e5"
    ): Response<ApiResponse>

}