package com.example.newsapp.newsapiclient.presention.di

import android.util.Log
import com.example.newsapp.BuildConfig
import com.example.newsapp.newsapiclient.data.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetModule {
    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        val infoInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                //.query(accountId)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
               // .header("Authorization",accountInfo.tokenType + " " + accountInfo.accessToken)
                .header("Accept", "application/json")
                .build()

            chain.proceed(newRequest)
        }

        val infoClient = OkHttpClient().newBuilder()
            .addInterceptor(infoInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(infoClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.i("TAG", "Calling retrofit.create")
        try {
            // How to get json data here
        }catch (e: Exception){
            Log.e("TAG", "Error", e);
        }
        Log.i("TAG", "Finished retrofit.create")
    }

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit):NewsApi{
        return retrofit.create(NewsApi::class.java)
    }
}