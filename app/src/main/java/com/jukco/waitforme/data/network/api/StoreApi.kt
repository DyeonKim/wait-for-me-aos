package com.jukco.waitforme.data.network.api

import com.jukco.waitforme.data.network.model.StoreDetailResponse
import com.jukco.waitforme.data.network.model.StoreResponse
import org.jetbrains.annotations.NotNull
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET("shop")
    suspend fun getStoreList(): List<StoreResponse>

    @GET("shop/{id}")
    suspend fun getStore(@Path("id") @NotNull id: Int): StoreDetailResponse
}