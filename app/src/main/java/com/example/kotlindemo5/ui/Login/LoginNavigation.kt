package com.example.kotlindemo5.ui.Login

import androidx.lifecycle.LiveData

interface LoginNavigation {
    fun loginSuccessfully(loginResponse: LiveData<String>)
}