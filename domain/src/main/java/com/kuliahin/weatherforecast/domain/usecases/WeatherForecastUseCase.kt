package com.kuliahin.weatherforecast.domain.usecases

import com.kuliahin.weatherforecast.domain.model.BulkResponse
import com.kuliahin.weatherforecast.domain.model.CityResponse
import com.kuliahin.weatherforecast.domain.repositories.WeatherForecastRepository

class WeatherForecastUseCase(
    private val weatherForecastRepository: WeatherForecastRepository,
) {
    suspend fun getWeatherForCities(): Result<BulkResponse> {
        return weatherForecastRepository.getWeatherForCities()
    }

    suspend fun getWeatherForCity(city: String): Result<CityResponse> {
        return weatherForecastRepository.getWeatherForCity(city)
    }
}
