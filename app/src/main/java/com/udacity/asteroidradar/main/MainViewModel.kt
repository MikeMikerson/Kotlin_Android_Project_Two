package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NasaAPI
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _apod = MutableLiveData<PictureOfDay>()
    val apod: LiveData<PictureOfDay>
        get() = _apod

    init {
        retrieveImageOfTheDay()
    }

    private fun retrieveImageOfTheDay() {
        viewModelScope.launch {
            try {
                _apod.value = NasaAPI.retrofitService.getImageOfTheDay(BuildConfig.API_KEY)
            } catch (e: Exception) {
                _apod.value = PictureOfDay(
                    "",
                    "",
                    "https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_960_720.jpg")
            }
        }
    }
}