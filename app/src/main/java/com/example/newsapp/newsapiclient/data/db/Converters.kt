package com.example.newsapp.newsapiclient.data.db

import androidx.room.TypeConverter
import com.example.newsapp.newsapiclient.data.model.Article
import com.example.newsapp.newsapiclient.data.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
     return   Source(name,name)

    }




}