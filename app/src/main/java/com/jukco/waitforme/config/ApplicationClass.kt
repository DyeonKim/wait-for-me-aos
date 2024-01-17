package com.jukco.waitforme.config

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jukco.waitforme.data.repository.AppContainer
import com.jukco.waitforme.data.repository.DefaultContainer
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class ApplicationClass : Application() {
    lateinit var container: AppContainer

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("") // TODO : 보안 처리해서 넣을 것
        .build()

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer(retrofit)
    }
}