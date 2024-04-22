package com.kuliahin.weatherforecast.ui.cities

data class CityWeather(
    val id: Long,
    val cityName: String,
    val temperature: Int,
    val forecast: List<DayForecast> = listOf(),
)

data class DayForecast(val id: Long, val date: String, val temperature: Int, val info: String)
