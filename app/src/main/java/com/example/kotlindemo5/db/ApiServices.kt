package com.example.kotlindemo5.db

import com.example.kotlindemo5.ui.Login.param.SignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

const val  BASE_URL = "http://127.0.0.1:8000/api/"
interface ApiServices {
    @POST("students/auth/signin")
    fun signIn(@Body signUp: SignUp) : Call<String>
//    @POST("students/auth/register")
////
}