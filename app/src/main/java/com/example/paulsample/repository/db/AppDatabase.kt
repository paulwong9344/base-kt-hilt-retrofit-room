package com.example.paulsample.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paulsample.repository.db.countries.CountriesDao
import com.example.paulsample.repository.db.news.NewsDao
import com.example.paulsample.repository.model.countries.Country
import com.example.paulsample.repository.model.news.News

/**
 * App Database
 * Define all entities and access Dao's here/ Each entity is a table.
 */
@Database(entities = [News::class, Country::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun countriesDao(): CountriesDao
}
