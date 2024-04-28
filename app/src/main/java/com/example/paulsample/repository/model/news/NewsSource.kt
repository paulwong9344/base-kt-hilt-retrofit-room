package com.example.paulsample.repository.model.news

import com.google.gson.annotations.SerializedName

/**
 * News source model describing the source details and the articles under it.
 */
data class NewsSource(
    @SerializedName("status") var status: String = "",
    @SerializedName("source") var source: String = "",
    @SerializedName("sortBy") var sortBy: String = "",
    @SerializedName("articles") var articles: List<News> = emptyList()
)
