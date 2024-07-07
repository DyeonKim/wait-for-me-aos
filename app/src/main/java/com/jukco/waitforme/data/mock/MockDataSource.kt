package com.jukco.waitforme.data.mock

import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.data.network.model.NoticeDetailResponse
import com.jukco.waitforme.data.network.model.NoticeResponse
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.SignInResponse
import com.jukco.waitforme.data.network.model.Token
import com.jukco.waitforme.data.network.model.UserInfoRes
import java.time.LocalDateTime

object MockDataSource {
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
    val signInRes = SignInResponse(
        phoneNumber = "01012345678",
        name = "테스트입니다.",
        isOwner = false,
        accessToken = accessToken,
        refreshToken = refreshToken,
    )

    val noticeList = listOf(
        NoticeResponse(0, "테스트입니다1", LocalDateTime.now()),
        NoticeResponse(1, "테스트입니다2", LocalDateTime.now()),
        NoticeResponse(2, "테스트입니다3", LocalDateTime.now()),
        NoticeResponse(3, "테스트입니다4", LocalDateTime.now()),
        NoticeResponse(4, "테스트입니다5", LocalDateTime.now()),
        NoticeResponse(5, "테스트입니다6", LocalDateTime.now()),
        NoticeResponse(6, "테스트입니다7", LocalDateTime.now()),
    )

    val noticeDetailList = listOf(
        NoticeDetailResponse(
            0,
            "테스트입니다1",
            "1직원정보 업데이트를 위해 NAHAGO 모바일앱을 통해 정보를 등록하여 주시기 바랍니다.\n\n이외 필수 기재사항은 첨부파일을 참고하여 주시기 바랍니다.",
            LocalDateTime.now(),
        ),
        NoticeDetailResponse(
            1,
            "테스트입니다2",
            "2직원정보 업데이트를 위해 NAHAGO 모바일앱을 통해 정보를 등록하여 주시기 바랍니다.\n\n이외 필수 기재사항은 첨부파일을 참고하여 주시기 바랍니다.",
            LocalDateTime.now(),
        ),
        NoticeDetailResponse(
            2,
            "테스트입니다3",
            "3직원정보 업데이트를 위해 NAHAGO 모바일앱을 통해 정보를 등록하여 주시기 바랍니다.\n\n이외 필수 기재사항은 첨부파일을 참고하여 주시기 바랍니다.",
            LocalDateTime.now(),
        ),
        NoticeDetailResponse(
            3,
            "테스트입니다4",
            "4직원정보 업데이트를 위해 NAHAGO 모바일앱을 통해 정보를 등록하여 주시기 바랍니다.\n\n이외 필수 기재사항은 첨부파일을 참고하여 주시기 바랍니다.",
            LocalDateTime.now(),
        ),
        NoticeDetailResponse(
            4,
            "테스트입니다5",
            "5직원정보 업데이트를 위해 NAHAGO 모바일앱을 통해 정보를 등록하여 주시기 바랍니다.\n\n이외 필수 기재사항은 첨부파일을 참고하여 주시기 바랍니다.",
            LocalDateTime.now(),
        ),
        NoticeDetailResponse(
            5,
            "테스트입니다6",
            "6직원정보 업데이트를 위해 NAHAGO 모바일앱을 통해 정보를 등록하여 주시기 바랍니다.\n\n이외 필수 기재사항은 첨부파일을 참고하여 주시기 바랍니다.",
            LocalDateTime.now(),
        ),
        NoticeDetailResponse(
            6,
            "테스트입니다7",
            "7직원정보 업데이트를 위해 NAHAGO 모바일앱을 통해 정보를 등록하여 주시기 바랍니다.\n\n이외 필수 기재사항은 첨부파일을 참고하여 주시기 바랍니다.",
            LocalDateTime.now(),
        ),
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
}