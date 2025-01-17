package com.jukco.waitforme.fake

import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.SignInResponse
import com.jukco.waitforme.data.network.model.SnsInfo
import com.jukco.waitforme.data.network.model.StoreDetailResponse
import com.jukco.waitforme.data.network.model.StoreResponse
import com.jukco.waitforme.data.network.model.Token
import com.jukco.waitforme.data.network.model.UserInfoRes

object FakeDataSource {
    val storeList = listOf(
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

    val storeDetailList = listOf(
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
            images = listOf(),
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
            images = listOf(),
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
            images = listOf(),
            startDate = "2023-11-24",
            endDate = "2024-02-15",
            openTime = "08:30",
            closeTime = "20:00",
            address = "주소입니다~~".repeat(10),
            snsList = listOf(SnsInfo("INSTAGRAM", "www.example.com")),
            isFavorite = false,
            isReserved = true,
        ),
        StoreDetailResponse(
            title = "스마일",
            host = "주말이다",
            description = "주말아 와라! ".repeat(100),
            images = listOf(),
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
    )

    val accessToken = Token(
        token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoi7YWM7Iqk7Yq47J6F64uI64ukLiIsImV4cCI6MTcwNzQwNTU4MiwiYXV0aG9yaXRpZXMiOiJVU0VSIn0.YF4wLj9m9IfJRVxiEFPTskFz8BlMPH6gaqmotlpI0iKt00KK42_ZFztOnYbNNV9vQjp-Mrn10f8nmv6UkCwKkQ",
        createdAt = "2024-02-08T23:49:42.828",
        expiredAt = "2024-02-09T00:19:42.828",
    )
    val refreshToken = Token(
        token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoi7YWM7Iqk7Yq47J6F64uI64ukLiIsImV4cCI6MTcwNzQwNzM4MiwiYXV0aG9yaXRpZXMiOiJVU0VSIn0.BEbmx1OW-6ulVUxvrF0RzvElMK8pWCkoLdnFrF9pKOJD4WdYAaSOS7mNYHJVggbi64GNTw1d1_RFUbClfqwe6g",
        createdAt = "2024-02-08T23:49:42.828",
        expiredAt = "2024-02-09T00:49:42.828",
    )
    val signInResponse = SignInResponse(
        phoneNumber = "01012345678",
        name = "테스트입니다.",
        isOwner = false,
        accessToken = accessToken,
        refreshToken = refreshToken,
    )

    val userInfoRes = UserInfoRes(
        provider = Provider.LOCAL,
        phoneNumber = "01012345678",
        name = "테스트입니다.",
        isOwner = false,
        birthedAt = null,
        genderType = GenderType.OTHER,
        profileImage = null,
    )

    val revisedUserInfoRes = UserInfoRes(
        provider = Provider.LOCAL,
        phoneNumber = "01012345678",
        name = "테스트라구요!",
        isOwner = false,
        birthedAt = "1995-01-18",
        genderType = GenderType.FEMALE,
        profileImage = null,
    )
}
