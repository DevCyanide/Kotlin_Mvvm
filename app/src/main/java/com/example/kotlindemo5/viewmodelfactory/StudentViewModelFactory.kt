package com.example.kotlindemo5.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo5.repository.StudentRepository
import com.example.kotlindemo5.ui.Register.RegisterViewModel

class StudentViewModelFactory(private val repository: StudentRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}