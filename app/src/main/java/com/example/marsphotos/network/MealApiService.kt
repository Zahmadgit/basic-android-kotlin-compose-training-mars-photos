package com.example.marsphotos.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealResponse
} 