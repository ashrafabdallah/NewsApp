package com.example.newsapp.newsapiclient.presention.di

import com.example.newsapp.newsapiclient.domain.repository.NewsRepository
import com.example.newsapp.newsapiclient.domain.usecase.GetSavedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GetSavedNewsUseCaseModule {

    @Singleton
    @Provides
    fun provideGetSavedNewsUseCase(newsRepository: NewsRepository): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(newsRepository)
    }
}