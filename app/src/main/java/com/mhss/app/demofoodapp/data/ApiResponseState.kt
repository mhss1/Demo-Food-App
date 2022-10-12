package com.mhss.app.demofoodapp.data

sealed class ApiResponseState {
    object Loading : ApiResponseState()
    data class Success(val data: ProductResponse) : ApiResponseState()
    data class Error(val error: String) : ApiResponseState()
}
