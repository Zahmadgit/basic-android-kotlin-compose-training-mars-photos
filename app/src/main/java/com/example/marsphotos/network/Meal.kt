package com.example.marsphotos.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(
    val meals: List<Meal>
)

@Serializable
data class Meal(
    @SerialName("idMeal")
    val id: String,
    @SerialName("strMeal")
    val name: String,
    @SerialName("strMealThumb")
    val imageUrl: String
) 