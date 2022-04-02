package com.example.newsapp.newsapiclient.presention.di

import com.example.newsapp.newsapiclient.domain.repository.NewsRepository
import com.example.newsapp.newsapiclient.domain.usecase.GetNewsHeadLinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class GetNewsHeadLinesUseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadLinesUseCase(newsRepository: NewsRepository): GetNewsHeadLinesUseCase
    {
        return GetNewsHeadLinesUseCase(newsRepository)
    }
}