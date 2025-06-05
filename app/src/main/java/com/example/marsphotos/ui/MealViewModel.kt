package com.example.marsphotos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.Meal
import com.example.marsphotos.network.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MealViewModel(private val repository: MealRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<MealUiState>(MealUiState.Loading)
    val uiState: StateFlow<MealUiState> = _uiState.asStateFlow()

    init {
        getSeafoodMeals()
    }

    private fun getSeafoodMeals() {
        viewModelScope.launch {
            _uiState.value = MealUiState.Loading
            try {
                val meals = repository.getSeafoodMeals()
                _uiState.value = MealUiState.Success(meals)
            } catch (e: Exception) {
                _uiState.value = MealUiState.Error
            }
        }
    }
}

sealed interface MealUiState {
    data class Success(val meals: List<Meal>) : MealUiState
    object Error : MealUiState
    object Loading : MealUiState
}

class MealViewModelFactory(private val repository: MealRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 