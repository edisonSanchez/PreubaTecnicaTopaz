package com.edisonsanchez.pruebatecnicatopaz.data


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPointsApi {

    @GET(GET_PRODUCTS)
    fun getProducts(): Call<ProductsResponse>

    @GET(GET_PRODUCT_BY_ID)
    fun getProductById(@Path("id") id: Int): Call<Product>

}