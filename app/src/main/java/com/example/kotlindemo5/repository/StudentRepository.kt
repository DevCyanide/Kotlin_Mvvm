package com.example.kotlindemo5.repository

import android.util.Log
import com.example.kotlindemo5.db.dao.StudentDao
import com.example.kotlindemo5.db.roomDB.RetrofitInstance
import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.ui.Login.param.SignUp
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentRepository(private val dao : StudentDao) {

    val students = dao.getAllStudent()

    suspend fun insert(students: Students):Long{
        return dao.insertStudent(students)
    }

    suspend fun login(student_num:String,password:String): List<Students> {
//        var okHttpClient:OkHttpClient
//        okHttpClient = OkHttpClient().newBuilder()
//            .addInterceptor(HttpIntercept())
//            .connectTimeout(5, TimeUnit.MINUTES)
//            .readTimeout(5, TimeUnit.MINUTES)
//            .writeTimeout(5, TimeUnit.MINUTES)
//            .build()
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()
//        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
//            .client(okHttpClient).addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build();
//        val service = retrofit.create(ApiServices::class.java)
//        service.signIn(SignUp(student_num,password)).enqueue(object : Callback<String>{
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.d("MYTAG-Error","Response : ${t.localizedMessage.toString()}")
//            }
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//            Log.d("MYTAG","Response : ${Gson().toJson(response.code())}")
//            }
//
//        })
        val call: Call<String>? = RetrofitInstance.apiService?.signIn(SignUp( student_num,password))
        call?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("MYTAG-Error","Response : ${t.localizedMessage.toString()}")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("MYTAG","Response : ${Gson().toJson(response.code())}")
            }

        })


        return dao.login(student_num,password)
    }




}