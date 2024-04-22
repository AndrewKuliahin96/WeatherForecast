package com.kuliahin.weatherforecast.domain.model

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
// TODO: Fix
    val change: Int,
)

data class BulkWeatherForCitiesRequest(
    @SerializedName("locations")
    val locations: List<Location>,
)

data class BulkResponse(
    @SerializedName("bulk")
    val bulk: List<BulkEntity>,
)

data class CityResponse(
    @SerializedName("custom_id")
    val customId: String,
    @SerializedName("q")
    val query: String,
    @SerializedName("location")
    val location: Location,
)

data class BulkEntity(
    @SerializedName("q")
    val query: BulkQuery,
)

data class BulkQuery(
    @SerializedName("custom_id")
    val customId: String,
    @SerializedName("q")
    val query: String,
    @SerializedName("location")
    val location: Location,
)

data class Location(
    @SerializedName("q")
    val query: String,
)
