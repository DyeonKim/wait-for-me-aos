package com.jukco.waitforme.data.repository

import com.jukco.waitforme.data.network.api.StoreApi
import com.jukco.waitforme.data.network.model.ImageInfo
import com.jukco.waitforme.data.network.model.SnsInfo
import com.jukco.waitforme.data.network.model.StoreDetailResponse
import com.jukco.waitforme.data.network.model.StoreResponse

interface StoreRepository {
    suspend fun getStoreList(): List<StoreResponse>

    suspend fun getStore(id: Int): StoreDetailResponse
}

class StoreRepositoryImplementation(
    private val storeApi: StoreApi,
) : StoreRepository {
    // TODO: 응답이 404 등의 오류인 경우도 생각하기, 서버 연결 전까지 목업데이터
    override suspend fun getStoreList(): List<StoreResponse> = storeApi.getStoreList()

    override suspend fun getStore(id: Int): StoreDetailResponse = storeApi.getStore(id)
}

class MockStoreRepository : StoreRepository {
    override suspend fun getStoreList(): List<StoreResponse> = listOf(
        StoreResponse(0, "", "핑크 홀리데이", "야놀자", 0, false),
        StoreResponse(1, "", "코카콜라", "코카콜라", 1, true),
        StoreResponse(2, "https://cdn.pixabay.com/photo/2016/11/23/15/14/jars-1853439_1280.jpg", "홀리데이", "여기어때", -1, true),
        StoreResponse(3, "", "사이다", "칠성", 0, false),
        StoreResponse(4, "https://cdn.pixabay.com/photo/2016/11/22/21/57/apparel-1850804_1280.jpg", "어쩌구", "저쩌구", 0, false),
        StoreResponse(5, "https://cdn.pixabay.com/photo/2017/08/07/19/46/shop-2607121_1280.jpg", "야호", "무", 0, false),
        StoreResponse(6, "", "스마일", "주말이다", 0, false),
        StoreResponse(7, "", "핑크", "Pink", 0, false),
        StoreResponse(8, "", "떠나요", "둘이서", 0, false),
        StoreResponse(9, "", "I만 다섯", "mbti", 0, false),
    )
    override suspend fun getStore(id: Int): StoreDetailResponse = listOf(
        StoreDetailResponse(
            title = "핑크 홀리데이",
            host = "야놀자",
            description = "핑크 홀리데이! ".repeat(100),
            images = listOf(),
            startDate = "2023-12-24",
            endDate = "2024-01-31",
            openTime = "11:30",
            closeTime = "20:00",
            address = "주소입니다~~",
            snsList = listOf(SnsInfo("INSTAGRAM", "@Sep20_N")),
            isFavorite = false,
            isReserved = false,
        ),
        StoreDetailResponse(
            title = "코카콜라",
            host = "코카콜라",
            description = "코카콜라! ".repeat(100),
            images = listOf(),
            startDate = "2023-12-25",
            endDate = "2024-01-14",
            openTime = "10:30",
            closeTime = "23:00",
            address = "주소입니다~~".repeat(2),
            snsList = listOf(),
            isFavorite = true,
            isReserved = false,
        ),
        StoreDetailResponse(
            title = "홀리데이",
            host = "여기어때",
            description = "여기어때 ".repeat(100),
            images = listOf(
                ImageInfo("MAIN", "https://cdn.pixabay.com/photo/2016/11/23/15/14/jars-1853439_1280.jpg"),
                ImageInfo("DETAIL", "https://cdn.pixabay.com/photo/2015/11/13/07/47/italy-1041660_1280.jpg"),
                ImageInfo("DETAIL", ""),
            ),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:00",
            address = "주소입니다~~".repeat(3),
            snsList = listOf(SnsInfo("FACEBOOK", "www.example.com")),
            isFavorite = true,
            isReserved = true,
        ),
        StoreDetailResponse(
            title = "사이다",
            host = "칠성",
            description = "칠성사이다 ".repeat(100),
            images = listOf(),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:00",
            address = "주소입니다~~".repeat(3),
            snsList = listOf(SnsInfo("FACEBOOK", "www.example.com")),
            isFavorite = true,
            isReserved = false,
        ),
        StoreDetailResponse(
            title = "어쩌구",
            host = "저쩌구",
            description = "어쩌구저쩌구 ".repeat(100),
            images = listOf(
                ImageInfo("MAIN", "https://cdn.pixabay.com/photo/2017/08/07/19/46/shop-2607121_1280.jpg"),
                ImageInfo("DETAIL", "https://cdn.pixabay.com/photo/2015/11/13/07/47/italy-1041660_1280.jpg"),
                ImageInfo("DETAIL", ""),
                ImageInfo("DETAIL", ""),
            ),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:00",
            address = "주소입니다~~".repeat(5),
            snsList = listOf(SnsInfo("FACEBOOK", "www.example.com")),
            isFavorite = false,
            isReserved = false,
        ),
        StoreDetailResponse(
            title = "야호",
            host = "무",
            description = "야호~~~~~~~~~~~~~! ".repeat(100),
            images = listOf(
                ImageInfo("MAIN", "https://cdn.pixabay.com/photo/2017/08/07/19/46/shop-2607121_1280.jpg"),
                ImageInfo("DETAIL", ""),
                ImageInfo("DETAIL", ""),
            ),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:00",
            address = "주소입니다~~".repeat(10),
            snsList = listOf(SnsInfo("FACEBOOK", "www.example.com")),
            isFavorite = false,
            isReserved = true,
        ),
        StoreDetailResponse(
            title = "스마일",
            host = "주말이다",
            description = "주말아 와라! ".repeat(100),
            images = listOf(ImageInfo("MAIN", "")),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:30",
            address = "주소입니다~~".repeat(3),
            snsList = listOf(SnsInfo("FACEBOOK", "www.example.com")),
            isFavorite = false,
            isReserved = false,
        ),
        StoreDetailResponse(
            title = "핑크",
            host = "Pink",
            description = "핑크 pink ".repeat(100),
            images = listOf(),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:00",
            address = "주소입니다~~".repeat(3),
            snsList = listOf(SnsInfo("FACEBOOK", "www.example.com")),
            isFavorite = false,
            isReserved = false,
        ),
        StoreDetailResponse(
            title = "떠나요",
            host = "둘이서",
            description = "제주도로 가자! ".repeat(100),
            images = listOf(),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:00",
            address = "주소입니다~~".repeat(3),
            snsList = listOf(SnsInfo("FACEBOOK", "www.example.com"), SnsInfo("INSTAGRAM", "@sep20_N")),
            isFavorite = false,
            isReserved = false,
        ),
        StoreDetailResponse(
            title = "I만 다섯",
            host = "mbti",
            description = "IIIII ".repeat(100),
            images = listOf(),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:00",
            address = "주소입니다~~".repeat(5),
            snsList = listOf(SnsInfo("INSTAGRAM", "@IonlyFive"), SnsInfo("FACEBOOK", "www.example.com")),
            isFavorite = false,
            isReserved = false,
        ),
    )[id]
}