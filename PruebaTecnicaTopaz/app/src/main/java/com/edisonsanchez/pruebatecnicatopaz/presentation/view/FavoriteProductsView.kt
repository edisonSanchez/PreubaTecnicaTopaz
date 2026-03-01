package com.edisonsanchez.pruebatecnicatopaz.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.edisonsanchez.pruebatecnicatopaz.data.Product
import com.edisonsanchez.pruebatecnicatopaz.presentation.viewModel.FavoriteProductsViewModel

@Composable
fun FavoriteProductsView(
    navController: NavController,
    viewModel: FavoriteProductsViewModel = hiltViewModel()
) {
    val favoriteProducts by viewModel.favoriteProducts.collectAsState()
    viewModel.getAllFavoriteProducts()
    favoriteProducts?.let { products ->
        if (products.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No se encontraron productos favoritos")
            }
        } else {
            LazyColumn {
                items(products) { favoriteProduct ->
                    val product = Product( favoriteProduct.id, favoriteProduct.title,
                        favoriteProduct.price, favoriteProduct.description, "",
                        emptyList(), favoriteProduct.thumbnail, 0.0)
                        ProductItem(product = product) { id ->
                            navController.navigate("${Details.route}/$id")
                        }
                }
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}