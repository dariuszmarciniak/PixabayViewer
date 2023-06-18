package com.example.codingchallenge.network

import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
    @GET("/api/")
    suspend fun searchImages(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): PixabayAPIResponse
}