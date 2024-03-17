package com.jukco.waitforme.data.network.api

import com.jukco.waitforme.data.network.model.LocalSignInRequest
import com.jukco.waitforme.data.network.model.SignInResponse
import com.jukco.waitforme.data.network.model.SocialSignInRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignApi {
    @POST("auth/local/sign-in")
    suspend fun localSignIn(@Body request: LocalSignInRequest): Response<SignInResponse>

    @POST("auth/sns/sign-in")
    suspend fun socialSignIn(@Body request: SocialSignInRequest): Response<SignInResponse>
}