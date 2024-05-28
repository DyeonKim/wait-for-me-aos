package com.jukco.waitforme.data.repository

import com.jukco.waitforme.data.network.api.SignApi
import com.jukco.waitforme.data.network.model.LocalSignInRequest
import com.jukco.waitforme.data.network.model.LocalSignUpRequest
import com.jukco.waitforme.data.network.model.PhoneNumCheckRequest
import com.jukco.waitforme.data.network.model.SignInResponse
import com.jukco.waitforme.data.network.model.SocialSignInRequest
import com.jukco.waitforme.data.network.model.SocialSignUpRequest
import retrofit2.Response

interface SignRepository {
    suspend fun localSignIn(signInReq: LocalSignInRequest): Response<SignInResponse>

    suspend fun socialSignIn(signInReq: SocialSignInRequest): Response<SignInResponse>

    suspend fun localSignUp(signUpReq: LocalSignUpRequest): Response<SignInResponse>

    suspend fun socialSignUp(signUpReq: SocialSignUpRequest): Response<SignInResponse>

    suspend fun checkDuplicateName(name: String): Response<Boolean>

    suspend fun requestAuthnNum(phoneNum: String): Response<Boolean>

    suspend fun checkPhoneNumberValidity(request: PhoneNumCheckRequest): Response<Boolean>
}

class SignRepositoryImplementation(
    private val signApi: SignApi,
) : SignRepository {
    override suspend fun localSignIn(signInReq: LocalSignInRequest): Response<SignInResponse> =
        signApi.localSignIn(signInReq)

    override suspend fun socialSignIn(signInReq: SocialSignInRequest): Response<SignInResponse> =
        signApi.socialSignIn(signInReq)

    override suspend fun localSignUp(signUpReq: LocalSignUpRequest): Response<SignInResponse> =
        signApi.localSignUp(signUpReq)

    override suspend fun socialSignUp(signUpReq: SocialSignUpRequest): Response<SignInResponse> =
        signApi.socialSignUp(signUpReq)

    override suspend fun checkDuplicateName(name: String): Response<Boolean> =
        signApi.checkDuplicateName(name)

    override suspend fun requestAuthnNum(phoneNum: String): Response<Boolean> =
        signApi.requestAuthnNum(phoneNum)

    override suspend fun checkPhoneNumberValidity(request: PhoneNumCheckRequest): Response<Boolean> =
        signApi.checkPhoneNumberValidity(request)
}