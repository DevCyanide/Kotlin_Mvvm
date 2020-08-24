package com.example.kotlindemo5.db

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpIntercept : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val newRequest: Request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}