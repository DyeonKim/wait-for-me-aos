package com.jukco.waitforme.fake

import com.jukco.waitforme.data.network.model.StoreResponse
import com.jukco.waitforme.data.repository.StoreRepository

class FakeStoreRepository : StoreRepository {
    override suspend fun getStoreList(): List<StoreResponse> = FakeDataSource.storeList
}