package com.kuliahin.weatherforecast.data.api

import com.kuliahin.weatherforecast.data.BuildConfig
import com.kuliahin.weatherforecast.domain.model.BulkResponse
import com.kuliahin.weatherforecast.domain.model.BulkWeatherForCitiesRequest
import com.kuliahin.weatherforecast.domain.model.CityResponse
import com.kuliahin.weatherforecast.domain.model.Location
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WeatherForecastApi {
    @GET("/search.json&key=${BuildConfig.API_KEY}")
    suspend fun getCities(
        @Query("country") country: String,
    ): Response<List<Location>>

    @GET("/current.json&q=bulk&key=${BuildConfig.API_KEY}")
    suspend fun getBulkWeatherForCities(
        @Body request: BulkWeatherForCitiesRequest,
    ): Response<BulkResponse>

    @GET("/current.json&key=${BuildConfig.API_KEY}")
    suspend fun getDetailedCityForecast(
        @Query("city") city: String,
    ): Response<CityResponse>
}

fun provideApiService(): WeatherForecastApi {
    val okHttpClientBuilder = OkHttpClient.Builder()

    okHttpClientBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)

    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }.let(okHttpClientBuilder::addInterceptor)

    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClientBuilder.build())
        .build()
        .create(WeatherForecastApi::class.java)
}

private const val TIMEOUT = 15L
