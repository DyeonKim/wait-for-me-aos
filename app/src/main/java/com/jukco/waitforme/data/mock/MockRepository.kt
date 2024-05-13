package com.jukco.waitforme.data.mock

import com.jukco.waitforme.data.network.model.LocalSignInRequest
import com.jukco.waitforme.data.network.model.LocalSignUpRequest
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.SignInResponse
import com.jukco.waitforme.data.network.model.SocialSignInRequest
import com.jukco.waitforme.data.network.model.SocialSignUpRequest
import com.jukco.waitforme.data.repository.SignRepository
import com.jukco.waitforme.data.repository.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import java.net.HttpURLConnection
import java.time.LocalDateTime

object MockTokenManager : TokenManager {
    override val providerFlow: Flow<String> = MutableStateFlow("")
    override val userNameFlow: Flow<String> = MutableStateFlow("")
    override val userPhoneNumberFlow: Flow<String> = MutableStateFlow("")
    override val isOwnerFlow: Flow<Boolean> = MutableStateFlow(false)
    override val accessTokenFlow: Flow<String> = MutableStateFlow("")
    override val accessTokenCreatedTimeFlow: Flow<LocalDateTime?> = MutableStateFlow(null)
    override val accessTokenExpiredTimeFlow: Flow<LocalDateTime?> = MutableStateFlow(null)
    override val refreshTokenFlow: Flow<String> = MutableStateFlow("")
    override val refreshTokenCreatedTimeFlow: Flow<LocalDateTime?> = MutableStateFlow(null)
    override val refreshTokenExpiredTimeFlow: Flow<LocalDateTime?> = MutableStateFlow(null)

    override suspend fun saveToken(provider: Provider, signInResponse: SignInResponse) {}

}

object MockSignRepository : SignRepository {
    override suspend fun localSignIn(signInReq: LocalSignInRequest): Response<SignInResponse> =
        Response.success(HttpURLConnection.HTTP_OK, MockDataSource.signInRes)

    override suspend fun socialSignIn(signInReq: SocialSignInRequest): Response<SignInResponse> =
        Response.success(HttpURLConnection.HTTP_NO_CONTENT, MockDataSource.signInRes)

    override suspend fun localSignUp(signUpReq: LocalSignUpRequest): Response<SignInResponse> =
        Response.success(HttpURLConnection.HTTP_OK, MockDataSource.signInRes)

    override suspend fun socialSignUp(signUpReq: SocialSignUpRequest): Response<SignInResponse> =
        Response.success(HttpURLConnection.HTTP_OK, MockDataSource.signInRes)

    override suspend fun checkDuplicateName(name: String): Response<Boolean> =
        Response.success(HttpURLConnection.HTTP_OK, false)

}