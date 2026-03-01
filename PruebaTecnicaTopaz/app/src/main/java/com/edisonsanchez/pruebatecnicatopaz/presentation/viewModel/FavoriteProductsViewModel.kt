package com.edisonsanchez.pruebatecnicatopaz.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.edisonsanchez.pruebatecnicatopaz.data.FavoriteProduct
import com.edisonsanchez.pruebatecnicatopaz.domain.UseCaseGetAllFavoriteProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteProductsViewModel @Inject constructor(
    private val useCaseGetAllFavoriteProducts: UseCaseGetAllFavoriteProducts) : ViewModel() {

        private val _favoriteProducts: MutableStateFlow<List<FavoriteProduct>?> =
            useCaseGetAllFavoriteProducts.favoriteProducts
        val favoriteProducts: StateFlow<List<FavoriteProduct>?> = _favoriteProducts


    fun getAllFavoriteProducts() {
        useCaseGetAllFavoriteProducts.invoke()
    }

}