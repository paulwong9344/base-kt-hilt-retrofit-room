package com.example.paulsample.repository.repo.countries

import com.example.paulsample.repository.model.countries.Country
import com.example.paulsample.utils.CountryNameMapping
import javax.inject.Inject

class CountriesRepositoryMapper @Inject constructor(
    private val textProvider: CountryRepositoryTextProvider,
) {

    fun toArrayListOfCountries(list: List<String>): ArrayList<Country>{
        val listOfCountries = ArrayList<Country>()
        list.forEach { item ->
            val country = Country().apply {
                countryName = item
                displayName = textProvider.getDisplayName(item)
                countryFagUrl = textProvider.getFagUrl(item)
                countryKey = CountryNameMapping.getCountryKey(item)
            }
            listOfCountries.add(country)
        }
        return listOfCountries
    }
}
