package com.jukco.waitforme.data.repository

import com.jukco.waitforme.data.network.api.NoticeApi
import com.jukco.waitforme.data.network.model.NoticeDetailResponse
import com.jukco.waitforme.data.network.model.NoticeResponse
import retrofit2.Response

interface NoticeRepository {
    suspend fun getNoticeList(): Response<List<NoticeResponse>>

    suspend fun getNotice(noticeId: Int): Response<NoticeDetailResponse>
}

class NoticeRepositoryImplementation(
    private val noticeApi: NoticeApi,
) : NoticeRepository {
    override suspend fun getNoticeList(): Response<List<NoticeResponse>> =
        noticeApi.getNoticeList()

    override suspend fun getNotice(noticeId: Int): Response<NoticeDetailResponse> =
        noticeApi.getNotice(noticeId)

}