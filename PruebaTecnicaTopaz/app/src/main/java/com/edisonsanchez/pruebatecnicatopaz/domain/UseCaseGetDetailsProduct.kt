package com.edisonsanchez.pruebatecnicatopaz.domain

import com.edisonsanchez.pruebatecnicatopaz.data.DataRepository
import com.edisonsanchez.pruebatecnicatopaz.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UseCaseGetDetailsProduct @Inject constructor(private val dataRepository: DataRepository) {

    val detailsProduct: MutableStateFlow<Product?> = dataRepository.detailsProduct

    fun invoke(productId: Int) = dataRepository.getDetailsProduct(productId)

}