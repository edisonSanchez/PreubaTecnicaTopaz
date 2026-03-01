package com.edisonsanchez.pruebatecnicatopaz.domain

import com.edisonsanchez.pruebatecnicatopaz.data.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UseCaseRemoveFavoriteProduct @Inject constructor(private val dataRepository: DataRepository) {

    val resultRemoveFavoriteProduct : MutableStateFlow<Int> =
        dataRepository.resultRemoveFavoriteProduct

    fun invoke(idProduct: Int){
        dataRepository.removeFavoriteProduct(idProduct)
    }
}