package com.example.kotlindemo5.db

import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.ui.Login.param.SignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiServices {
        @POST("students/auth/signin")
        fun signIn(@Body signUp: SignUp) : Call<String>
        @POST("students/auth/signin")
        fun login(@Body signUp: SignUp): Call<Students>
//    @POST("students/auth/register")

////
}