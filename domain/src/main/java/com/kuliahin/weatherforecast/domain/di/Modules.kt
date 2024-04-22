package com.kuliahin.weatherforecast.domain.di

import com.kuliahin.weatherforecast.domain.usecases.WeatherForecastUseCase
import org.koin.dsl.module

val domainModule =
    module {
        single { WeatherForecastUseCase(get()) }
    }
