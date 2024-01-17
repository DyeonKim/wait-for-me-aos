package com.jukco.waitforme.data.repository

import com.jukco.waitforme.R
import com.jukco.waitforme.data.network.api.StoreApi
import com.jukco.waitforme.data.network.model.StoreResponse

interface StoreRepository {
    suspend fun getStoreList(): List<StoreResponse>
}

class StoreRepositoryImplementation(
    private val storeApi: StoreApi
) : StoreRepository {
    // TODO: 응답이 404 등의 오류인 경우도 생각하기
    override suspend fun getStoreList(): List<StoreResponse> = storeApi.getStoreList()
}