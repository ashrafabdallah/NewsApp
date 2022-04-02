package com.example.newsapp.newsapiclient.data.repostory

import com.example.newsapp.newsapiclient.data.model.ApiResponse
import com.example.newsapp.newsapiclient.data.model.Article
import com.example.newsapp.newsapiclient.data.repostory.dataresource.NewsLocalDataSource
import com.example.newsapp.newsapiclient.data.repostory.dataresource.RemoteDataSource
import com.example.newsapp.newsapiclient.data.util.Resource
import com.example.newsapp.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepostoryIMPL(private val remoteDataSource: RemoteDataSource,private val newsLocalDataSource: NewsLocalDataSource) : NewsRepository {
    override suspend fun getNewsHeadLines(
        country: String,
        page: Int,
    ): Resource<ApiResponse> {
        return responseToResultResource(remoteDataSource.getTopNewsHeadLines(country, page))
    }

    override suspend fun getSearchNews(
        country: String,
        query: String,
        page: Int
    ): Resource<ApiResponse> {
      return responseToResultResource(remoteDataSource.getSearchTopNewsHeadLines(country,query,page))
    }


    private fun responseToResultResource(response: Response<ApiResponse>): Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)

            }
        }
        return Resource.Error(response.message())

    }


    override suspend fun saveNewInDB(article: Article) {
        newsLocalDataSource.saveArtical(article)
    }

    override suspend fun deleteSavedNewsInDB(article: Article) {
       newsLocalDataSource.deleteNewsFromDb(article)
    }

    override fun getSavedNewsFromDB(): Flow<List<Article>> {
       return newsLocalDataSource.getSavedNewsFromDB()
    }
}