package com.example.kotlindemo5.ui.Register

import androidx.lifecycle.LiveData

interface RegisterNavigation {
    fun onSuccessRegister(registerResponse: LiveData<String>)
}