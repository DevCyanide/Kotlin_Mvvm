package com.example.kotlindemo5.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.repository.StudentRepository
import kotlinx.coroutines.launch

class LoginViewModel(val repository: StudentRepository) : ViewModel() {

    var isLogin : MutableLiveData<List<Students>> = MutableLiveData()
    var presenter : LoginNavigation? = null


    fun login(student_num : String,password : String){
           val loginResponse = repository.login(student_num,password)
            presenter?.loginSuccessfully(loginResponse)
        }
    }
