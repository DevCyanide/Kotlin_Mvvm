package com.example.kotlindemo5.ui.Register

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.repository.StudentRepository
import kotlinx.coroutines.launch
import java.util.*

class RegisterViewModel(private val repository: StudentRepository) : ViewModel(), Observable {

    val students = repository.students


     fun insertData(student_num:String,firstname:String,lastname:String,password:String){
        viewModelScope.launch {
            repository.insert(Students(0,student_num,firstname,lastname,password))
        }
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}