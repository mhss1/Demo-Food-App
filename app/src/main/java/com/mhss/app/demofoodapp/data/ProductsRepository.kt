package com.mhss.app.demofoodapp.data

import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsApi: ProductsApi
) {

    suspend fun getProducts() = flow {
        emit(ApiResponseState.Loading)
        try {
            val products = productsApi.getProducts()
            emit(ApiResponseState.Success(products))
        } catch (e: IOException) {
            emit(ApiResponseState.Error("No internet connection"))
        } catch (e: Exception) {
            emit(ApiResponseState.Error("Something went wrong"))
        }
    }
}