package com.mhss.app.demofoodapp.data

import retrofit2.http.GET

interface ProductsApi {

    @GET("/products")
    suspend fun getProducts(): ProductResponse
}