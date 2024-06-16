package com.jukco.waitforme.data.repository

import com.jukco.waitforme.data.network.api.UserApi
import com.jukco.waitforme.data.network.model.UserInfoRequest
import com.jukco.waitforme.data.network.model.UserInfoRes
import retrofit2.Response

interface UserRepository {
    suspend fun getUserInfo(): Response<UserInfoRes>

    suspend fun editUserInfo(userInfoReq: UserInfoRequest): Response<UserInfoRes>

    suspend fun withdraw(reason: String): Response<Boolean>
}

class UserRepositoryImplementation(
    private val userApi: UserApi,
) : UserRepository {
    override suspend fun getUserInfo(): Response<UserInfoRes> = userApi.getUserInfo()

    override suspend fun editUserInfo(userInfoReq: UserInfoRequest): Response<UserInfoRes> = userApi.editUserInfo(userInfoReq)

    override suspend fun withdraw(reason: String): Response<Boolean> = userApi.withdraw(reason)
}