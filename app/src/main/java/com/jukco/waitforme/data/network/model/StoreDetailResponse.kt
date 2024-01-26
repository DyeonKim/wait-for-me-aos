package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDetailResponse(
    @SerialName(value = "title") val title: String = "스토어 명",
    @SerialName(value = "host") val host: String = "주최자",
    @SerialName(value = "description") val description: String = "팝업스토어 설명",
    @SerialName(value = "imageInfo") val images: List<ImageInfo> = emptyList(),
    @SerialName(value = "startedAt") val startDate: String = "0000-00-00",
    @SerialName(value = "endedAt") val endDate: String = "0000-00-00",
    @SerialName(value = "openedAt") val openTime: String = "00:00",
    @SerialName(value = "closedAt") val closeTime: String = "00:00",
    @SerialName(value = "address") val address: String = "주소",
    @SerialName(value = "snsInfo") val snsList: List<SnsInfo> = listOf(),
    @SerialName(value = "isFavorite") val isFavorite: Boolean = false,
    @SerialName(value = "isReserved") val isReserved: Boolean = true,
) {
    val snsMap = snsList.associate { it.snsType to it.snsId }
    val operatingPeriod = "${startDate.replace("-", ".")} ~ ${endDate.replace("-", ".")}"
    val operatingTime = "$openTime ~ $closeTime"
}


@Serializable
data class ImageInfo(
    @SerialName(value = "type") val type: String, // TODO: enum?
    @SerialName(value = "path") val path: String,
)

@Serializable
data class SnsInfo(
    @SerialName(value = "snsType") val snsType: String, // TODO: enum?
    @SerialName(value = "snsId") val snsId: String
)
