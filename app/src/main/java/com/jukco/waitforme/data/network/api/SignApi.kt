package com.jukco.waitforme.data.network.api

import com.jukco.waitforme.data.network.model.LocalSignInRequest
import com.jukco.waitforme.data.network.model.LocalSignUpRequest
import com.jukco.waitforme.data.network.model.SignInResponse
import com.jukco.waitforme.data.network.model.SocialSignInRequest
import com.jukco.waitforme.data.network.model.SocialSignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignApi {
    @POST("auth/local/sign-in")
    suspend fun localSignIn(@Body signInReq: LocalSignInRequest): Response<SignInResponse>

    @POST("auth/sns/sign-in")
    suspend fun socialSignIn(@Body signInReq: SocialSignInRequest): Response<SignInResponse>

    @POST("auth/local/sign-up")
    suspend fun localSignUp(@Body signUpReq: LocalSignUpRequest): Response<SignInResponse>

    @POST("auth/sns/sign-up")
    suspend fun socialSignUp(@Body signUpReq: SocialSignUpRequest): Response<SignInResponse>
}