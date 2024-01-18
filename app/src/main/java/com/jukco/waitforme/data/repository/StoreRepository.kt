package com.jukco.waitforme.data.repository

import com.jukco.waitforme.R
import com.jukco.waitforme.data.network.api.StoreApi
import com.jukco.waitforme.data.network.model.StoreResponse

interface StoreRepository {
    suspend fun getStoreList(): List<StoreResponse>
}

class StoreRepositoryImplementation(
    private val storeApi: StoreApi
) : StoreRepository {
    // TODO: 응답이 404 등의 오류인 경우도 생각하기, 서버 연결 전까지 목업데이터
//    override suspend fun getStoreList(): List<StoreResponse> = storeApi.getStoreList()
    override suspend fun getStoreList(): List<StoreResponse> = listOf(
        StoreResponse(0, "", "핑크 홀리데이", "야놀자", 0, false),
        StoreResponse(1, "", "코카콜라", "코카콜라", 1, true),
        StoreResponse(2, "", "홀리데이", "여기어때", -1, true),
        StoreResponse(3, "", "사이다", "칠성", 0, false),
        StoreResponse(4, "", "어쩌구", "저쩌구", 0, false),
        StoreResponse(5, "", "야호", "무", 0, false),
        StoreResponse(6, "", "스마일", "주말이다", 0, false),
        StoreResponse(7, "", "핑크", "Pink", 0, false),
        StoreResponse(8, "", "떠나요", "둘이서", 0, false),
        StoreResponse(9, "", "I만 다섯", "mbti", 0, false),
    )
}