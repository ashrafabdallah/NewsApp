package com.example.newsapp.newsapiclient.presention.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.example.newsapp.newsapiclient.data.model.ApiResponse
import com.example.newsapp.newsapiclient.data.model.Article
import com.example.newsapp.newsapiclient.data.util.Resource
import com.example.newsapp.newsapiclient.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadLinesUseCase,
    private val getSearchNewsUseCase: GetSearchNewsUseCase,
    private val savedNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
    ,private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : AndroidViewModel(app) {
    val newsHeadLines: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getNewsHeadlinesUseCase.executeGetNewsHeadLines(country, page)
                newsHeadLines.postValue(apiResult)
            } else {
                newsHeadLines.postValue(Resource.Error("Internet is not available"))
            }

        } catch (e: Exception) {
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }


    // search
    var searchNewsHeadLines: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()
    fun getSearchNews(country: String, query: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            searchNewsHeadLines.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {

                    val result = getSearchNewsUseCase.executeGetSearchNews(country, query, page)
                    searchNewsHeadLines.postValue(result)
                } else {
                    searchNewsHeadLines.postValue(Resource.Error("No Internet..........."))
                }
            } catch (e: Exception) {
                searchNewsHeadLines.postValue(Resource.Error(e.message.toString()))
            }


        }

    // save news in local DataBase
    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        savedNewsUseCase.executeSaveNewInDB(article)
    }

    // get Saved News from Local DataBase
    fun getSavedNews() = liveData {

        getSavedNewsUseCase.executegetSavedNewsFromDB().collect{
            emit(it)
        }
    }



     //  Delete From Local DataBase
    fun deleteFromLocalDataBase(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        deleteSavedNewsUseCase.executeDeleteSaveNewFromDB(article)

     }

}

