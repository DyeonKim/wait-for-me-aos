package com.jukco.waitforme.data.network.api

import com.jukco.waitforme.data.network.model.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignApi {
    @POST("auth/local/sign-in")
    suspend fun localSignIn(@Body phoneNumAndPassword: Map<String, String>): Response<SignInResponse>
}