package com.example.kotlindemo5.db

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val BaseURl = "http://192.168.100.3/kotlindemo/public/api/"
    private const val LIVE_HOST_NAME = ""

    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null
    private val BASE_URL = BaseURl
    val retrofitInstance: Retrofit?
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            if (okHttpClient == null) {
                okHttpClient = OkHttpClient().newBuilder()
                    .addInterceptor(HttpIntercept())
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .build()
            }
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit
        }
    val apiService: ApiServices?
        get() = retrofitInstance?.create(ApiServices::class.java)

    enum class Mode {
        test, live
    }
}