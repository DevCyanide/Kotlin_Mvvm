package com.example.kotlindemo5.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindemo5.db.dao.StudentDao
import com.example.kotlindemo5.db.RetrofitInstance
import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.ui.Login.param.SignIn
import com.example.kotlindemo5.ui.Register.param.Register
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentRepository(private val dao : StudentDao) {

    val students = dao.getAllStudent()



    suspend fun insert(students: Students):Long{
        return dao.insertStudent(students)
    }
//        fun signIn(student_num:String,password :String){
//            val signin:Call<Students>? = RetrofitInstance.apiService.signIn(Students)
//        }

        fun login(student_num:String,password:String): LiveData<String> {
        val loginResponse = MutableLiveData<String>()

        val call: Call<String>? = RetrofitInstance.apiService?.signIn(SignIn( student_num,password))
        call?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("MYTAG-Error","Response : ${t.localizedMessage.toString()}")
                loginResponse.value = t.message
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("MYTAG", "Response : ${Gson().toJson(response.code())}")
                    if (response.code() == 200) {
                        loginResponse.value = "Successfull Login"
                    } else if(response.code()>=404) {
                        loginResponse.value = "Invalid Username/Password"
                    }
                    else{
                      loginResponse.value= response.errorBody().toString()
                    }
            }
        })
//        dao.login(student_num,password)
        return loginResponse
    }
    fun register(student_num: String,firstname:String,lastname:String,password: String):LiveData<String>{
        val registerResponse = MutableLiveData<String>()

        val registerCall: Call<Register>? = RetrofitInstance.apiService?.regCustomer(Register(student_num,firstname,lastname,password))
        registerCall?.enqueue(object : Callback<Register>{
            override fun onFailure(call: Call<Register>, t: Throwable) {
                registerResponse.value = t.localizedMessage.toString()
            }

            override fun onResponse(call: Call<Register>, response: Response<Register>) {
             registerResponse.value = response.body().toString()
            }

        })
        return registerResponse
    }
    fun getAllStudent():LiveData<List<Students>>{
        val getResponse = MutableLiveData<List<Students>>()
    val getStudent : Call<List<Students>>? = RetrofitInstance.apiService?.getStudents()
        getStudent?.enqueue(object : Callback<List<Students>>{

            override fun onFailure(call: Call<List<Students>>, t: Throwable) {
                Log.d("MYTAG-errorss",t.localizedMessage.toString())
            }
            override fun onResponse(
                call: Call<List<Students>>, response: Response<List<Students>>) {
                getResponse.value = response.body()
            }

        })

        return getResponse
    }

}