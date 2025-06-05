package com.example.marsphotos.network

class MealRepository(private val apiService: MealApiService) {
    suspend fun getSeafoodMeals(): List<Meal> {
        return try {
            apiService.getMealsByCategory("Seafood").meals
        } catch (e: Exception) {
            emptyList()
        }
    }
} 