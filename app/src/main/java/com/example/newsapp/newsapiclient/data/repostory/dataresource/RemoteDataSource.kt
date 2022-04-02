package com.example.newsapp.newsapiclient.data.repostory.dataresource


import com.example.newsapp.newsapiclient.data.model.ApiResponse

import retrofit2.Response


interface RemoteDataSource {
    suspend fun getTopNewsHeadLines(country:String,page:Int
    ): Response<ApiResponse>
    suspend fun getSearchTopNewsHeadLines(country:String,query:String,page:Int
    ): Response<ApiResponse>

}