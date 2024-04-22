package com.kuliahin.weatherforecast.domain.repositories

import com.kuliahin.weatherforecast.domain.model.BulkResponse
import com.kuliahin.weatherforecast.domain.model.CityResponse

interface WeatherForecastRepository {
    suspend fun getWeatherForCities(): Result<BulkResponse>

    suspend fun getWeatherForCity(city: String): Result<CityResponse>
}
