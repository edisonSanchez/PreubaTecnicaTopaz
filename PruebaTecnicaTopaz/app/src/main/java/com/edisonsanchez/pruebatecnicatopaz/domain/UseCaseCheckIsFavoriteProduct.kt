package com.edisonsanchez.pruebatecnicatopaz.domain

import com.edisonsanchez.pruebatecnicatopaz.data.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UseCaseCheckIsFavoriteProduct @Inject constructor(private val dataRepository: DataRepository) {

    val resultCheckIsFavoriteProduct: MutableStateFlow<Boolean> =
        dataRepository.resultCheckIsFavoriteProduct

    fun invoke(idProduct: Int) {
        dataRepository.checkIsFavoriteProduct(idProduct)
    }

}