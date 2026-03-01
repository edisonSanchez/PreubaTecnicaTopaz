package com.edisonsanchez.pruebatecnicatopaz.domain

import com.edisonsanchez.pruebatecnicatopaz.data.DataRepository
import com.edisonsanchez.pruebatecnicatopaz.data.FavoriteProduct
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UseCaseAddFavoriteProduct @Inject constructor(private val dataRepository: DataRepository) {

    val resultAddFavoriteProduct: MutableStateFlow<Long> = dataRepository.resultAddFavoriteProduct

    fun invoke(favoriteProduct: FavoriteProduct) {
        dataRepository.addFavoriteProduct(favoriteProduct)
    }

}