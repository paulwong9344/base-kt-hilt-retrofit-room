package com.example.paulsample.viewmodel.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paulsample.repository.model.countries.Country
import com.example.paulsample.repository.repo.countries.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A container for [Country] related data to show on the UI.
 */

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository,
) : ViewModel() {

    /**
     * Loading countries from database
     */
    private var countries: MutableLiveData<List<Country>> = MutableLiveData()

    fun getCountries(): LiveData<List<Country>> {
        viewModelScope.launch {
            val result = countriesRepository.getCountries()
            countries.postValue(result)
        }
        return countries
    }
}
