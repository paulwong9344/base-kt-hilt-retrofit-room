package com.example.paulsample.repository.repo.countries

import com.example.paulsample.repository.db.countries.CountriesDao
import com.example.paulsample.repository.model.countries.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 */
@Singleton
class CountriesRepository @Inject constructor(
    private val countriesDao: CountriesDao,
    private val mapper: CountriesRepositoryMapper,
    private val resourcesProvider: CountriesRepositoryResourcesProvider,
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    suspend fun getCountries(): List<Country> {
        withContext(Dispatchers.IO) {
            resourcesProvider.getListFromAssets()?.let {
                countriesDao.deleteAllCountries()
                countriesDao.insertCountries(mapper.toArrayListOfCountries(it))
            }
        }
        return countriesDao.getCountries()
    }
}
