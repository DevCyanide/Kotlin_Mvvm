package com.example.kotlindemo5.ui.Register

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlindemo5.repository.StudentRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: StudentRepository) : ViewModel(), Observable {

    val students = repository.students
    var presenter : RegisterNavigation? = null




     fun insertData(student_num:String,firstname:String,lastname:String,password:String){
        viewModelScope.launch {
         val registerResponse =   repository.register(student_num,firstname,lastname,password)
            presenter?.onSuccessRegister(registerResponse)
        }
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}