package com.edisonsanchez.pruebatecnicatopaz.domain

import com.edisonsanchez.pruebatecnicatopaz.data.DataRepository
import com.edisonsanchez.pruebatecnicatopaz.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UseCaseGetProducts @Inject constructor(private val dataRepository: DataRepository) {

    val products : MutableStateFlow<List<Product>?> = dataRepository.products

     fun invoke() = dataRepository.getProducts()

}