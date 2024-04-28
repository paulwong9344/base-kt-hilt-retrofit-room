package com.example.paulsample.di.modules

import android.content.Context
import androidx.room.Room
import com.example.paulsample.BuildConfig
import com.example.paulsample.repository.api.ApiServices
import com.example.paulsample.repository.api.network.LiveDataCallAdapterFactoryForRetrofit
import com.example.paulsample.repository.db.AppDatabase
import com.example.paulsample.repository.db.countries.CountriesDao
import com.example.paulsample.repository.db.news.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides app AppDatabase
     * Room啟動時將檢測version是否發生增加，如果有，那麼將找到Migration去執行特定的操作。
     * fallbackToDestructiveMigration() 將會刪除數據庫並重建
     */
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "news-db")
            .fallbackToDestructiveMigration().build()

    /**
     * Provides CountriesDao an object to access Countries table from Database
     */
    @Singleton
    @Provides
    fun provideCountriesDao(db: AppDatabase): CountriesDao = db.countriesDao()

    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideNewsService(): ApiServices {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(ApiServices::class.java)
    }

    /**
     * Provides NewsDao an object to access NewsArticles table from Database
     */
    @Singleton
    @Provides
    fun provideNewsDao(db: AppDatabase): NewsDao = db.newsDao()
}
