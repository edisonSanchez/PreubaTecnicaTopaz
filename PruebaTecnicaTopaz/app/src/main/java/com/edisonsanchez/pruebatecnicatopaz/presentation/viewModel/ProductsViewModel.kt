package com.edisonsanchez.pruebatecnicatopaz.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edisonsanchez.pruebatecnicatopaz.data.Product
import com.edisonsanchez.pruebatecnicatopaz.domain.UseCaseGetProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val useCaseGetProducts: UseCaseGetProducts) :
    ViewModel() {

    private val _products : MutableStateFlow<List<Product>?> = useCaseGetProducts.products
    val products: StateFlow<List<Product>?> = _products

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                useCaseGetProducts.invoke()
            } catch (e: Exception) {
                _products.value = emptyList()
            }
        }
    }

}