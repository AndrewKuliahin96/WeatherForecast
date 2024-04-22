package com.kuliahin.weatherforecast.data.sources

import com.kuliahin.weatherforecast.data.api.WeatherForecastApi
import com.kuliahin.weatherforecast.domain.model.BulkResponse
import com.kuliahin.weatherforecast.domain.model.BulkWeatherForCitiesRequest
import com.kuliahin.weatherforecast.domain.model.CityResponse
import com.kuliahin.weatherforecast.domain.model.Location
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class WeatherForecastRemoteDataSource(private val weatherForecastApi: WeatherForecastApi) {
    suspend fun getWeatherForCities(): Result<BulkResponse> {
        return getBulkWeatherForCities(getCities().getOrThrow())
    }

    suspend fun getWeatherForCity(city: String): Result<CityResponse> {
        return makeApiCall {
            weatherForecastApi.getDetailedCityForecast(city = city)
        }
    }

    private suspend fun getCities(): Result<List<Location>> {
        return makeApiCall {
            // TODO: Make option to choose country
            weatherForecastApi.getCities(country = "Ukraine")
        }
    }

    private suspend fun getBulkWeatherForCities(cities: List<Location>): Result<BulkResponse> {
        return makeApiCall {
            weatherForecastApi.getBulkWeatherForCities(request = BulkWeatherForCitiesRequest(cities))
        }
    }

    private suspend fun <T : Any> makeApiCall(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call.invoke()

            if (!response.isSuccessful) {
                return Result.failure(Exception("Response failed"))
            }

            return try {
                response.body()?.let { Result.success(it) } ?: throw Exception("Response failed")
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        } catch (e: Exception) {
            val error =
                when (e) {
                    is UnknownHostException,
                    is SocketTimeoutException,
                    is ConnectException,
                    -> Exception("Network error")

                    else -> e
                }

            return Result.failure(error)
        }
    }
}
