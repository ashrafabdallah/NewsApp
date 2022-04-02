package com.example.newsapp.newsapiclient.presention.di

import android.app.Application
import com.example.newsapp.newsapiclient.domain.usecase.*
import com.example.newsapp.newsapiclient.presention.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NewsViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        app: Application,
        getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase,
        getSearchNewsUseCase: GetSearchNewsUseCase,
        savedNewsUseCase: SaveNewsUseCase, getSavedNewsUseCase: GetSavedNewsUseCase
    ,deleteSavedNewsUseCase: DeleteSavedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            app, getNewsHeadLinesUseCase,
            getSearchNewsUseCase, savedNewsUseCase,
            getSavedNewsUseCase,deleteSavedNewsUseCase
        )
    }
}