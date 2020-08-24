package com.example.kotlindemo5.ui.Login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginViewModel(val repository: StudentRepository) : ViewModel() {

    var isLogin : MutableLiveData<List<Students>> = MutableLiveData()

    fun login(student_num : String,password : String){
     var presenter : LoginNavigation? =null
        viewModelScope.launch {
            //Api in Login
            isLogin.value= repository.login(student_num,password)
            presenter?.loginSuccessfully()
        }
    }
}