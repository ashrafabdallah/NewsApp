package com.example.newsapp.newsapiclient.data.model


import com.example.newsapp.newsapiclient.data.model.Article
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)