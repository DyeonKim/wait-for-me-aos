package com.jukco.waitforme.data.network.api

import com.jukco.waitforme.data.network.model.StoreResponse
import retrofit2.http.GET

interface StoreApi {
    @GET("shop")
    suspend fun getStoreList(): List<StoreResponse>
}