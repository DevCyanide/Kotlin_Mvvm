package com.example.kotlindemo5.db

import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.ui.Login.param.SignIn
import com.example.kotlindemo5.ui.Register.param.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiServices {
        @POST("students/auth/signin")
        fun signIn(@Body signUp: SignIn) : Call<String>

//        @POST("students/auth/signin")
//        fun login(@Body signUp: SignIn): Call<Students>
        @POST("students/auth/register")
        fun regCustomer(@Body register : Register):Call<Register>
        @GET("students")
        fun getStudents() :Call<List<Students>>

}