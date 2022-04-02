package com.example.newsapp.newsapiclient.presention.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.newsapiclient.domain.usecase.*

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase,
    private val getSearchNewsUseCase: GetSearchNewsUseCase,
    private val savedNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
    ,private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(app, getNewsHeadLinesUseCase
                ,getSearchNewsUseCase,savedNewsUseCase
            ,getSavedNewsUseCase,deleteSavedNewsUseCase
            ) as T

        }
        throw  IllegalAccessException("Unknown View Model Class ......")
    }

}