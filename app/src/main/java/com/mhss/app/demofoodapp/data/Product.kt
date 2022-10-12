package com.mhss.app.demofoodapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val id: Int,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
): Parcelable