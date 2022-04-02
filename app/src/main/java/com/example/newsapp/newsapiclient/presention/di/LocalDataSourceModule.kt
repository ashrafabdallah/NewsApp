package com.example.newsapp.newsapiclient.presention.di

import com.example.newsapp.newsapiclient.data.db.ArticalDao
import com.example.newsapp.newsapiclient.data.repostory.dataresource.NewsLocalDataSource
import com.example.newsapp.newsapiclient.data.repostory.dataresourceimpl.NewsLocalDataSourceIMPL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(articalDao: ArticalDao): NewsLocalDataSource {
        return NewsLocalDataSourceIMPL(articalDao)
    }
}