package com.jukco.waitforme.fake

import com.jukco.waitforme.data.network.api.StoreApi
import com.jukco.waitforme.data.network.model.StoreDetailResponse
import com.jukco.waitforme.data.network.model.StoreResponse

class FakeStoreApi : StoreApi {
    override suspend fun getStoreList(): List<StoreResponse> = FakeDataSource.storeList

    override suspend fun getStore(id: Int): StoreDetailResponse = FakeDataSource.storeDetailList[id]
}