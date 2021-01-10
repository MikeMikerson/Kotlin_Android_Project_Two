package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _imgUrl = MutableLiveData<String>()
    val imgUrl: LiveData<String>
        get() = _imgUrl

    init {
        // TODO: Change value into something meaningful
        _imgUrl.value = "https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_960_720.jpg"
    }
}