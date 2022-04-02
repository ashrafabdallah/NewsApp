package com.example.newsapp.newsapiclient.presention.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.newsapiclient.data.db.ArticalDao
import com.example.newsapp.newsapiclient.data.db.ArticleRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(app:Application):ArticleRoomDataBase{
        return Room.databaseBuilder(app,ArticleRoomDataBase::class.java,"news_database")
            // This will Allow Room to destructively replace database tables, if Migrations, that would migrate old database
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticalDAo(articleRoomDataBase:ArticleRoomDataBase):ArticalDao{
        return articleRoomDataBase.getArticalDao()
    }
}