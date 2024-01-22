package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDetailResponse(
    @SerialName(value = "title") val title: String,
    @SerialName(value = "host") val host: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "imageInfo") val images: List<ImageInfo>,
    @SerialName(value = "startedAt") val startDate: String,
    @SerialName(value = "endedAt") val endDate: String,
    @SerialName(value = "openedAt") val openTime: String,
    @SerialName(value = "closedAt") val closeTime: String,
    @SerialName(value = "address") val address: String,
    @SerialName(value = "snsInfo") val snsList: List<SnsInfo> = listOf(),
    @SerialName(value = "isFavorite") val isFavorite: Boolean,
    @SerialName(value = "isReserved") val isReserved: Boolean
) {
    val snsMap = snsList.associate { it.snsType to it.snsId }
}


@Serializable
data class ImageInfo(
    @SerialName(value = "type") val type: String, // TODO: enum?
    @SerialName(value = "path") val path: String
)

@Serializable
data class SnsInfo(
    @SerialName(value = "snsType") val snsType: String, // TODO: enum?
    @SerialName(value = "snsId") val snsId: String
)
