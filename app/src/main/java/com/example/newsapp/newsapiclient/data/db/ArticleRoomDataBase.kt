package com.example.newsapp.newsapiclient.data.db

import androidx.room.*
import com.example.newsapp.newsapiclient.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleRoomDataBase : RoomDatabase(){

    abstract fun getArticalDao():ArticalDao

}