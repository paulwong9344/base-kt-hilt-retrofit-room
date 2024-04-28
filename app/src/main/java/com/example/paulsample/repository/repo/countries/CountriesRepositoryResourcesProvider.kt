package com.example.paulsample.repository.repo.countries

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountriesRepositoryResourcesProvider @Inject constructor(
    @ApplicationContext val context: Context,
) {

    suspend fun getListFromAssets(): List<String>? = withContext(Dispatchers.IO) {
        val asList = context.assets.list("countries")?.asList<String>()
        asList
    }
}
