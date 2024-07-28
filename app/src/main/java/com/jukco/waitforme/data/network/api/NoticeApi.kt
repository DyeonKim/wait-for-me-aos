package com.jukco.waitforme.data.network.api

import com.jukco.waitforme.data.network.model.NoticeDetailResponse
import com.jukco.waitforme.data.network.model.NoticeResponse
import retrofit2.Response
import retrofit2.http.GET

interface NoticeApi {
    @GET("notice")
    suspend fun getNoticeList(): Response<List<NoticeResponse>>

    @GET("notice/{noticeId}")
    suspend fun getNotice(noticeId: Int): Response<NoticeDetailResponse>
}