package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

/**
 * An interface for a NASA service that fetches data from the NASA Open API
 */
interface NasaService {
    /**
     * Retrieves the NASA image of the day and returns a URL of that image.
     */
    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") key: String): PictureOfDay
}

object NasaAPI {
    val retrofitService: NasaService by lazy { retrofit.create(NasaService::class.java) }
}