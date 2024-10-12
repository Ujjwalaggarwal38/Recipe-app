package com.example.recipeapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categoryState= mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> get() =_categoryState

    init {
        fetchCategory()
    }
    private fun fetchCategory(){
        viewModelScope.launch {
            try {
                val response = reciepeService.getCategories()
                _categoryState.value=_categoryState.value.copy(
                    list=response.categories,
                    loading = false,
                    error = null
                )

            }
            catch (e: Exception){
                _categoryState.value=_categoryState.value.copy(
                    loading = false,
                    error = "Error Fetching Categories ${e.message}"
                )
            }
        }

    }

    data class RecipeState(
        val loading: Boolean = true,
        val list:List<Category> = emptyList(),
        val error: String?=null
    )
}
