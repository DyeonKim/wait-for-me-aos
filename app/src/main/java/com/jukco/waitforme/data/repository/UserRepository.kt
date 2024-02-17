package com.jukco.waitforme.data.repository

import com.jukco.waitforme.data.network.api.UserApi
import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.UserInfoRequest
import com.jukco.waitforme.data.network.model.UserInfoRes
import retrofit2.Response

interface UserRepository {
    suspend fun getUserInfo(): Response<UserInfoRes>

    suspend fun editUserInfo(userInfoReq: UserInfoRequest): Response<UserInfoRes>
}

class UserRepositoryImplementation(
    private val userApi: UserApi,
) : UserRepository {
    override suspend fun getUserInfo(): Response<UserInfoRes> = userApi.getUserInfo()

    override suspend fun editUserInfo(userInfoReq: UserInfoRequest): Response<UserInfoRes> = userApi.editUserInfo(userInfoReq)

}

object MockUserRepository : UserRepository {
    private val userInfoRes = UserInfoRes(
        provider = Provider.LOCAL,
        phoneNumber = "01012345678",
        name = "테스트입니다.",
        isOwner = false,
        birthedAt = null,
        genderType = GenderType.OTHER,
        profileImage = null,
    )

    override suspend fun getUserInfo(): Response<UserInfoRes> = Response.success(userInfoRes)

    override suspend fun editUserInfo(userInfoReq: UserInfoRequest): Response<UserInfoRes>
    = Response.success(
        userInfoRes.copy(
            name = userInfoReq.name,
            birthedAt = userInfoReq.birthedAt,
            genderType = userInfoReq.genderType,
            profileImage = userInfoReq.profileImage
        )
    )

}