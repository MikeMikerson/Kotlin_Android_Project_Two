package com.udacity.asteroidradar.main

import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NasaAPI
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel : ViewModel() {
    private val _apod = MutableLiveData<PictureOfDay>()
    val apod: LiveData<PictureOfDay>
        get() = _apod

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    init {
        retrieveImageOfTheDay()
        retrieveListOfAsteroids()
    }

    private fun retrieveImageOfTheDay() {
        viewModelScope.launch {
            try {
                _apod.value = NasaAPI.retrofitService.getImageOfTheDay(BuildConfig.API_KEY)
            } catch (e: Exception) {
                _apod.value = PictureOfDay(
                    "2021-01-13",
                    "2021-01-19",
                    "https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_960_720.jpg"
                )
            }
        }
    }

    private fun retrieveListOfAsteroids() {
        viewModelScope.launch {
            try {
                val response = NasaAPI.retrofitService.getNeoFeed(
                    null,
                    null,
                    BuildConfig.API_KEY)

                val responseObject = JSONObject(response)
                _asteroids.value = parseAsteroidsJsonResult(responseObject)
            } catch (e: Exception) {
                _asteroids.value = ArrayList()
            }
        }
    }

    // TODO: Check if this is really necessary
    class Factory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel() as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }}