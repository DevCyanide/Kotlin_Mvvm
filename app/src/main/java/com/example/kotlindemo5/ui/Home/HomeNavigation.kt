package com.example.kotlindemo5.ui.Home

import androidx.lifecycle.LiveData
import com.example.kotlindemo5.model.Students

interface HomeNavigation {
    fun getAllData(data:LiveData<List<Students>>)
}