package com.edisonsanchez.pruebatecnicatopaz.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.edisonsanchez.pruebatecnicatopaz.data.FavoriteProduct
import com.edisonsanchez.pruebatecnicatopaz.data.Product
import com.edisonsanchez.pruebatecnicatopaz.domain.UseCaseAddFavoriteProduct
import com.edisonsanchez.pruebatecnicatopaz.domain.UseCaseCheckIsFavoriteProduct
import com.edisonsanchez.pruebatecnicatopaz.domain.UseCaseGetDetailsProduct
import com.edisonsanchez.pruebatecnicatopaz.domain.UseCaseRemoveFavoriteProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsProductViewModel @Inject constructor(
    private val useCaseGetDetailsProduct: UseCaseGetDetailsProduct,
    private val useCaseAddFavoriteProduct: UseCaseAddFavoriteProduct,
    private val useCaseCheckIsFavoriteProduct: UseCaseCheckIsFavoriteProduct,
    private val useCaseRemoveFavoriteProduct: UseCaseRemoveFavoriteProduct
) :
    ViewModel() {

    private val _detailsProduct: MutableStateFlow<Product?> = useCaseGetDetailsProduct.detailsProduct
    val detailsProduct: StateFlow<Product?> = _detailsProduct
    private val _resultAddFavoriteProduct: MutableStateFlow<Long> =
        useCaseAddFavoriteProduct.resultAddFavoriteProduct
    val resultAddFavoriteProduct: StateFlow<Long> = _resultAddFavoriteProduct
    private val _resultRemoveFavoriteProduct: MutableStateFlow<Int> =
        useCaseRemoveFavoriteProduct.resultRemoveFavoriteProduct
    val resultRemoveFavoriteProduct: StateFlow<Int> = _resultRemoveFavoriteProduct
    private val _resultCheckIsFavoriteProduct: MutableStateFlow<Boolean> =
        useCaseCheckIsFavoriteProduct.resultCheckIsFavoriteProduct
    val resultCheckIsFavoriteProduct: StateFlow<Boolean> = _resultCheckIsFavoriteProduct



    fun getDetailsProduct(id: Int){
        useCaseGetDetailsProduct.invoke(id)
    }

    fun addFavoriteProduct(product: Product){
        val favoriteProduct = FavoriteProduct(
            product.id,
            product.title,
            product.price,
            product.description,
            product.thumbnail
        )
        useCaseAddFavoriteProduct.invoke(favoriteProduct)
    }

    fun removeFavoriteProduct(idProduct: Int){
        useCaseRemoveFavoriteProduct.invoke(idProduct)
    }

    fun checkIsFavoriteProduct(idProduct: Int) {
        useCaseCheckIsFavoriteProduct.invoke(idProduct)
    }

}