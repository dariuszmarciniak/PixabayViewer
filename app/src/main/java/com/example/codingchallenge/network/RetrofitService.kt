package com.example.codingchallenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: PixabayService by lazy {
        retrofit.create(PixabayService::class.java)
    }
}