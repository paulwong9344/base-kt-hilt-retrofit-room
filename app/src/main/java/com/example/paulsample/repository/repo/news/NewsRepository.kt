package com.example.paulsample.repository.repo.news

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.paulsample.BuildConfig
import com.example.paulsample.app.AppExecutors
import com.example.paulsample.repository.api.ApiServices
import com.example.paulsample.repository.api.network.NetworkAndDBBoundResource
import com.example.paulsample.repository.api.network.NetworkBoundResource
import com.example.paulsample.repository.api.network.Resource
import com.example.paulsample.repository.db.news.NewsDao
import com.example.paulsample.repository.model.news.News
import com.example.paulsample.repository.model.news.NewsSource
import com.example.paulsample.utils.ConnectivityUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val apiServices: ApiServices,
    @ApplicationContext val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    fun getNewsArticles(countryShortKey: String): LiveData<Resource<List<News>?>> {
        val data = HashMap<String, String>()
        data[data_country] = countryShortKey
        data[data_apiKey] = BuildConfig.NEWS_API_KEY

        return object : NetworkAndDBBoundResource<List<News>, NewsSource>(appExecutors) {
            override fun saveCallResult(item: NewsSource) {
                if (item.articles.isNotEmpty()) {
                    newsDao.deleteAllArticles()
                    newsDao.insertArticles(item.articles)
                }
            }

            override fun shouldFetch(data: List<News>?) = (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = newsDao.getNewsArticles()

            override fun createCall() = apiServices.getNewsSource(data)
        }.asLiveData()
    }

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     * LiveData<Resource<NewsSource>>
     */
    fun getNewsArticlesFromServerOnly(countryShortKey: String): LiveData<Resource<NewsSource>> {

        val data = HashMap<String, String>()
        data[data_country] = countryShortKey
        data[data_apiKey] = BuildConfig.NEWS_API_KEY

        return object : NetworkBoundResource<NewsSource>() {
            override fun createCall(): LiveData<Resource<NewsSource>> {
                return apiServices.getNewsSource(data)
            }
        }.asLiveData()
    }

    companion object {
        const val data_country = "country"
        const val data_apiKey = "apiKey"
    }
}
