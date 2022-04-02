package com.example.newsapp.newsapiclient.presention.di

import com.example.newsapp.newsapiclient.domain.repository.NewsRepository
import com.example.newsapp.newsapiclient.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SaveNewsHeadLinesUseCase {
    @Singleton
    @Provides
    fun provideSaveNewsHeadLinesUseCase(newsRepository: NewsRepository): SaveNewsUseCase {
        return SaveNewsUseCase(newsRepository)
    }
}