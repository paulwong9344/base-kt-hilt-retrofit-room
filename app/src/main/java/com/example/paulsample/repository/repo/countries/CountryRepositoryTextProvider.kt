package com.example.paulsample.repository.repo.countries

import javax.inject.Inject

class CountryRepositoryTextProvider @Inject constructor() {

    fun getDisplayName(name: String): String =
        name.replace("_", " ").replace(".png", "")

    fun getFagUrl(name: String): String = "file:///android_asset/countries/$name"
}
