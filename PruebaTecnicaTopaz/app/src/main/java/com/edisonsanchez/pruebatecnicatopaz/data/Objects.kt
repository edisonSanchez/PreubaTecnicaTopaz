package com.edisonsanchez.pruebatecnicatopaz.data

import androidx.compose.ui.graphics.vector.ImageVector

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val images: List<String>,
    val thumbnail: String,
    val rating: Double
)

data class ProductsResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

