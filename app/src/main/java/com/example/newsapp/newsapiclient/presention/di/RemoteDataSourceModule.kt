package com.example.newsapp.newsapiclient.presention.di

import com.example.newsapp.newsapiclient.data.api.NewsApi
import com.example.newsapp.newsapiclient.data.repostory.dataresource.RemoteDataSource
import com.example.newsapp.newsapiclient.data.repostory.dataresourceimpl.RemoteDataSourceIMPL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteResource( newsApi: NewsApi):RemoteDataSource
    {
        return RemoteDataSourceIMPL(newsApi)
    }

}