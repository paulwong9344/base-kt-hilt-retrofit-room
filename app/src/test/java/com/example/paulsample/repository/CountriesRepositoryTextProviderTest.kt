package com.example.paulsample.repository

import com.example.paulsample.repository.repo.countries.CountryRepositoryTextProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class CountriesRepositoryTextProviderTest {

    @InjectMocks
    lateinit var textProvider: CountryRepositoryTextProvider

    @Test
    fun `verify when image name is entered, the underscore is replaced with space and png name is removed`() {
        // Given
        val sample = "Hong_Kong.png"
        val expected = "Hong Kong"

        // When + Then
        assertEquals(textProvider.getDisplayName(sample), expected)
    }

    @Test
    fun `verify when image name is entered, the android assert file path is returned`() {
        // Given
        val sample = "France.png"
        val expected = "file:///android_asset/countries/France.png"

        // When + Then
        assertEquals(textProvider.getFagUrl(sample), expected)
    }
}
