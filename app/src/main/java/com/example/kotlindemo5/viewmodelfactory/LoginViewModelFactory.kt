package com.example.kotlindemo5.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo5.repository.StudentRepository
import com.example.kotlindemo5.ui.Login.LoginViewModel
import com.example.kotlindemo5.ui.Register.RegisterViewModel

class LoginViewModelFactory(private val repository: StudentRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}