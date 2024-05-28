package com.jukco.waitforme.data.network.api

import com.jukco.waitforme.data.network.model.UserInfoRequest
import com.jukco.waitforme.data.network.model.UserInfoRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApi {
    @GET("user/info")
    suspend fun getUserInfo(): Response<UserInfoRes>

    @PUT("user/info")
    suspend fun editUserInfo(@Body userInfoReq: UserInfoRequest): Response<UserInfoRes>

    @DELETE("user/withdraw")
    suspend fun withdraw(@Body reason: String): Response<Boolean>
}