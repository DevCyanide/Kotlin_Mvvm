package com.example.kotlindemo5.repository

import android.util.Log
import com.example.kotlindemo5.db.ApiServices
import com.example.kotlindemo5.db.BASE_URL

import com.example.kotlindemo5.db.dao.StudentDao
import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.ui.Login.param.SignUp
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentRepository(private val dao : StudentDao) {

    val students = dao.getAllStudent()

    suspend fun insert(students: Students):Long{
        return dao.insertStudent(students)
    }

    suspend fun login(student_num:String,password:String): List<Students> {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(ApiServices::class.java)
        service.signIn(SignUp(student_num,password)).enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("MYTAG-Error","Response : ${t.localizedMessage.toString()}")
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
            Log.d("MYTAG-Success","Response : ${Gson().toJson(response.body())}")
            }

        })
        return dao.login(student_num,password)
    }




}