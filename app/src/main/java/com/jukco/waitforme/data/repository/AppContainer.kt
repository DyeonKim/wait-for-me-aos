package com.jukco.waitforme.data.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jukco.waitforme.BuildConfig
import com.jukco.waitforme.data.network.api.SignApi
import com.jukco.waitforme.data.network.api.StoreApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val storeRepository: StoreRepository
    val signRepository: SignRepository
}

class DefaultContainer : AppContainer {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BuildConfig.SERVER_URL)
        .build()

    private val storeApi: StoreApi by lazy {
        retrofit.create(StoreApi::class.java)
    }
    private val signApi: SignApi by lazy {
        retrofit.create(SignApi::class.java)
    }

    // TODO : 서버 연결 전까지 임시 Repository
    override val storeRepository: StoreRepository by lazy {
//        StoreRepositoryImplementation(storeApi)
        MockStoreRepository()
    }
    override val signRepository: SignRepository by lazy {
        SignRepositoryImplementation(signApi)
    }
}
