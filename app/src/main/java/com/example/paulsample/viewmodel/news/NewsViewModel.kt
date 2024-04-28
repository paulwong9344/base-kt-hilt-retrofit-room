package com.example.paulsample.viewmodel.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.paulsample.repository.api.network.Resource
import com.example.paulsample.repository.model.news.News
import com.example.paulsample.repository.repo.news.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    private fun newsArticles(countryKey: String): LiveData<Resource<List<News>?>> =
        newsRepository.getNewsArticles(countryKey)

    fun getNewsArticles(countryKey: String) = newsArticles(countryKey)

    /**
     * Loading news articles from internet only
     */
    private fun newsArticlesFromOnlyServer(countryKey: String) =
        newsRepository.getNewsArticlesFromServerOnly(countryKey)

    fun getNewsArticlesFromServer(countryKey: String) = newsArticlesFromOnlyServer(countryKey)
}
