package com.kuliahin.weatherforecast.data.repositories

import com.kuliahin.weatherforecast.data.sources.WeatherForecastLocalDataSource
import com.kuliahin.weatherforecast.data.sources.WeatherForecastRemoteDataSource
import com.kuliahin.weatherforecast.domain.model.BulkResponse
import com.kuliahin.weatherforecast.domain.model.CityResponse
import com.kuliahin.weatherforecast.domain.repositories.WeatherForecastRepository

class WeatherForecastRepositoryImpl(
    private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource,
    private val weatherForecastLocalDataSource: WeatherForecastLocalDataSource,
) : WeatherForecastRepository {
    override suspend fun getWeatherForCities(): Result<BulkResponse> {
        return weatherForecastRemoteDataSource.getWeatherForCities()
    }

    override suspend fun getWeatherForCity(city: String): Result<CityResponse> {
        return weatherForecastRemoteDataSource.getWeatherForCity(city)
    }
}
