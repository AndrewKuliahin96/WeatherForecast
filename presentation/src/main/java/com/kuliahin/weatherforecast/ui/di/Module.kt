package com.kuliahin.weatherforecast.ui.di

import com.kuliahin.weatherforecast.ui.cities.CitiesListViewModel
import com.kuliahin.weatherforecast.ui.details.DetailedCityForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule =
    module {
        viewModel { DetailedCityForecastViewModel(get()) }
        viewModel { CitiesListViewModel(get()) }
    }
