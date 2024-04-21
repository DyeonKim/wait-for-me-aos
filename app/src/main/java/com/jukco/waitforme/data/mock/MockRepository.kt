package com.jukco.waitforme.data.mock

import com.jukco.waitforme.data.network.model.LocalSignInRequest
import com.jukco.waitforme.data.network.model.LocalSignUpRequest
import com.jukco.waitforme.data.network.model.SignInResponse
import com.jukco.waitforme.data.network.model.SocialSignInRequest
import com.jukco.waitforme.data.network.model.SocialSignUpRequest
import com.jukco.waitforme.data.repository.SignRepository
import retrofit2.Response
import java.net.HttpURLConnection

object MockSignRepository : SignRepository {
    override suspend fun localSignIn(signInReq: LocalSignInRequest): Response<SignInResponse> =
        Response.success(HttpURLConnection.HTTP_OK, MockDataSource.signInRes)

    override suspend fun socialSignIn(signInReq: SocialSignInRequest): Response<SignInResponse> =
        Response.success(HttpURLConnection.HTTP_NO_CONTENT, MockDataSource.signInRes)

    override suspend fun localSignUp(signUpReq: LocalSignUpRequest): Response<SignInResponse> =
        Response.success(HttpURLConnection.HTTP_OK, MockDataSource.signInRes)

    override suspend fun socialSignUp(signUpReq: SocialSignUpRequest): Response<SignInResponse> =
        Response.success(HttpURLConnection.HTTP_OK, MockDataSource.signInRes)

}