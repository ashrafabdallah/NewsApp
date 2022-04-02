package com.example.newsapp.newsapiclient.presention.di

import com.example.newsapp.newsapiclient.presention.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NewAdapterModule {

    @Singleton
    @Provides
    fun provideNewAdapter():NewsAdapter{
        return NewsAdapter()
    }
}