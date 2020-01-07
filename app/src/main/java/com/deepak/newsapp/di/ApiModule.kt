package com.deepak.newsapp.di

import android.app.Application
import androidx.room.Room
import com.deepak.newsapp.BuildConfig
import com.deepak.newsapp.data.local.NewsDatabase
import com.deepak.newsapp.data.remote.DataApi
import com.deepak.newsapp.utils.livedata.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        private const val DB_NAME = "news-db"
    }

    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.cache(cache)
        httpClient.addInterceptor(loggingInterceptor)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()

    @Provides
    fun provideDataApi(retrofit: Retrofit): DataApi = retrofit.create(
        DataApi::class.java)

    @Provides
    @Singleton
    fun provideDataBase(application: Application) =
        Room.databaseBuilder(application, NewsDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideDao(database: NewsDatabase) = database.newsDao()
}