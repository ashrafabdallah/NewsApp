package com.example.newsapp.newsapiclient.presention.di

import com.example.newsapp.newsapiclient.data.repostory.NewsRepostoryIMPL
import com.example.newsapp.newsapiclient.data.repostory.dataresource.NewsLocalDataSource
import com.example.newsapp.newsapiclient.data.repostory.dataresource.RemoteDataSource
import com.example.newsapp.newsapiclient.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsRepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepostory(remoteDataSource: RemoteDataSource,newsLocalDataSource: NewsLocalDataSource): NewsRepository {
        return NewsRepostoryIMPL(remoteDataSource,newsLocalDataSource)
    }
}