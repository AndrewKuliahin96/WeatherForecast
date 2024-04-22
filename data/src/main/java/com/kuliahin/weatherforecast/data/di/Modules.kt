package com.kuliahin.weatherforecast.data.di

import com.kuliahin.weatherforecast.data.api.provideApiService
import com.kuliahin.weatherforecast.data.repositories.WeatherForecastRepositoryImpl
import com.kuliahin.weatherforecast.data.sources.WeatherForecastLocalDataSource
import com.kuliahin.weatherforecast.data.sources.WeatherForecastRemoteDataSource
import org.koin.dsl.module

val dataModule =
    module {
        single { WeatherForecastRemoteDataSource(get()) }
        single { WeatherForecastLocalDataSource() }
        single { provideApiService() }
        single { WeatherForecastRepositoryImpl(get(), get()) }
    }
