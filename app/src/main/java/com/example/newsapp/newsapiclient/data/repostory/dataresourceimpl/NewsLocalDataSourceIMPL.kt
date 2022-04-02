package com.example.newsapp.newsapiclient.data.repostory.dataresourceimpl

import com.example.newsapp.newsapiclient.data.db.ArticalDao
import com.example.newsapp.newsapiclient.data.model.Article
import com.example.newsapp.newsapiclient.data.repostory.dataresource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceIMPL(private val articalDao: ArticalDao):NewsLocalDataSource {
    override suspend fun saveArtical(article: Article) {
        articalDao.saveArtical(article)
    }

    override fun getSavedNewsFromDB(): Flow<List<Article>> {
        return articalDao.getSavedArticale()
    }

    override suspend fun deleteNewsFromDb(article: Article) {
        articalDao.deleteArtical(article)
    }
}