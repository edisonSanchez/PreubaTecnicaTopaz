package com.edisonsanchez.pruebatecnicatopaz.domain

import com.edisonsanchez.pruebatecnicatopaz.data.DataRepository
import com.edisonsanchez.pruebatecnicatopaz.data.FavoriteProduct
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UseCaseGetAllFavoriteProducts @Inject constructor(private val dataRepository: DataRepository) {

    val favoriteProducts: MutableStateFlow<List<FavoriteProduct>?> = dataRepository.favoriteProducts

    fun invoke() {
        dataRepository.getAllFavoriteProducts()
    }


}