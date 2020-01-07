package com.deepak.newsapp.data.remote

import androidx.lifecycle.LiveData
import com.deepak.newsapp.BuildConfig
import com.deepak.newsapp.data.model.NewsSource
import com.deepak.newsapp.utils.network.Resource
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApi {

    @GET(Endpoints.NEWS)
    fun fetchNews(@Query("api-key") apiKey : String = BuildConfig.API_KEY): LiveData<Resource<NewsSource>>
}