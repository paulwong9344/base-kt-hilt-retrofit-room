package com.example.paulsample.repository.api

import androidx.lifecycle.LiveData
import com.example.paulsample.repository.api.network.Resource
import com.example.paulsample.repository.model.news.NewsSource
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiServices {
    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Url would be something like this top-headlines?country=my&apiKey=XYZ
     */
    @GET("top-headlines")
    fun getNewsSource(@QueryMap options: Map<String, String>): LiveData<Resource<NewsSource>>
}
