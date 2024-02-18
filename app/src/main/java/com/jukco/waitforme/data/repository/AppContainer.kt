package com.jukco.waitforme.data.repository

import com.jukco.waitforme.data.network.api.StoreApi
import retrofit2.Retrofit

interface AppContainer {
    val storeRepository: StoreRepository
}

class DefaultContainer(retrofit: Retrofit) : AppContainer {
    private val storeApi: StoreApi by lazy {
        retrofit.create(StoreApi::class.java)
    }

    // TODO : 서버 연결 전까지 임시 Repository
//    override val storeRepository: StoreRepository by lazy {
//        StoreRepositoryImplementation(storeApi)
//    }
    override val storeRepository: StoreRepository by lazy {
        MockStoreRepository()
    }
}