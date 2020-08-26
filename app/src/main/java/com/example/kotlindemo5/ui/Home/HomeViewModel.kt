package com.example.kotlindemo5.ui.Home

import androidx.lifecycle.ViewModel
import com.example.kotlindemo5.repository.StudentRepository
import com.example.kotlindemo5.ui.Login.LoginNavigation

class HomeViewModel(val repository: StudentRepository) :ViewModel() {

    var presenter : HomeNavigation? = null

    fun getAllStudent(){
        val getResponse = repository.getAllStudent()
        presenter?.getAllData(getResponse)
    }

}