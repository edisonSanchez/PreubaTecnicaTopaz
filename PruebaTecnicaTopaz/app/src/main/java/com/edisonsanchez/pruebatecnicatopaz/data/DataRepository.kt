package com.edisonsanchez.pruebatecnicatopaz.data

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DataRepository @Inject constructor(
    private val endPointsApi: EndPointsApi,
    private val favoriteProductsDao: FavoriteProductsDao
) {

    val products: MutableStateFlow<List<Product>?> = MutableStateFlow(null)
    val detailsProduct: MutableStateFlow<Product?> = MutableStateFlow(null)
    val favoriteProducts: MutableStateFlow<List<FavoriteProduct>?> = MutableStateFlow(null)
    val resultAddFavoriteProduct: MutableStateFlow<Long> = MutableStateFlow(0)
    val resultRemoveFavoriteProduct: MutableStateFlow<Int> = MutableStateFlow(-1)
    val resultCheckIsFavoriteProduct: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun getProducts() {
        endPointsApi.getProducts().enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse?>?,
                response: Response<ProductsResponse?>?
            ) {
                if (response != null && response.isSuccessful) {
                    products.value = response.body()?.products ?: emptyList()
                } else {
                    products.value = emptyList()
                }
            }

            override fun onFailure(
                call: Call<ProductsResponse?>?,
                t: Throwable?
            ) {
                products.value = emptyList()
            }

        })
    }

    fun getDetailsProduct(productId: Int) {
        endPointsApi.getProductById(productId).enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product?>?,
                response: Response<Product?>?
            ) {
                if (response != null && response.isSuccessful) {
                    detailsProduct.value = response.body()
                } else {
                    detailsProduct.value = null
                }
            }

            override fun onFailure(
                call: Call<Product?>?,
                t: Throwable?
            ) {
                detailsProduct.value = null
            }

        })
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllFavoriteProducts() {
        GlobalScope.launch(Dispatchers.IO) {
            val products = favoriteProductsDao.getAll()
            withContext(Dispatchers.Main) {
                favoriteProducts.value = products
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun addFavoriteProduct(favoriteProduct: FavoriteProduct) {
        GlobalScope.launch(Dispatchers.IO) {
            val id = favoriteProductsDao.insertFavoriteProduct(favoriteProduct)
            withContext(Dispatchers.Main) {
                resultAddFavoriteProduct.value = id
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun removeFavoriteProduct(idProduct: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val rows = favoriteProductsDao.deleteById(idProduct)
            withContext(Dispatchers.Main) {
                resultRemoveFavoriteProduct.value = rows
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun checkIsFavoriteProduct(idProduct: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val product = favoriteProductsDao.getById(idProduct)
            withContext(Dispatchers.Main) {
                product?.let {
                    resultCheckIsFavoriteProduct.value = true
                } ?: run {
                    resultCheckIsFavoriteProduct.value = false
                }
            }
        }
    }

}