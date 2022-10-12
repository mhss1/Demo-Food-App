package com.mhss.app.demofoodapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.demofoodapp.data.ApiResponseState
import com.mhss.app.demofoodapp.data.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private var _state: MutableStateFlow<ApiResponseState> = MutableStateFlow(ApiResponseState.Loading)
    val state: StateFlow<ApiResponseState>
        get() = _state

    fun refreshProducts(){
        viewModelScope.launch {
            productsRepository.getProducts()
                .onEach {
                    _state.value = it
                }
                .launchIn(viewModelScope)
        }
    }

    init {
        refreshProducts()
    }
}