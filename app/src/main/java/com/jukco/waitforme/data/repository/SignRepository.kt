package com.jukco.waitforme.data.repository

import com.jukco.waitforme.data.network.api.SignApi
import com.jukco.waitforme.data.network.model.SignInResponse
import retrofit2.Response

interface SignRepository {
    suspend fun localSignIn(phoneNumAndPassword: Map<String, String>): Response<SignInResponse>
}

class SignRepositoryImplementation(
    private val signApi: SignApi,
) : SignRepository {
    override suspend fun localSignIn(phoneNumAndPassword: Map<String, String>): Response<SignInResponse> =
        signApi.localSignIn(phoneNumAndPassword)
}