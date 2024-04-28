package com.example.paulsample.repository.db.countries

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paulsample.repository.model.countries.Country

/**
 * Abstracts access to the countries database
 */
@Dao
abstract class CountriesDao {

    /**
     * Insert countries into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCountries(countries: List<Country>)

    /**
     * Get all countries from database
     */
    @Query("SELECT * FROM countries_table")
    abstract suspend fun getCountries(): List<Country>

    /**
     * Delete all countries from database
     */
    @Query("DELETE FROM countries_table")
    abstract suspend fun deleteAllCountries()
}
