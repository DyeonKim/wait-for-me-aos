package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDetailResponse(
    @SerialName(value = "title") val title: String,
    @SerialName(value = "host") val host: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "imageInfo") val imageInfo: List<ImageInfo>,
    @SerialName(value = "startedAt") val startedAt: String,
    @SerialName(value = "endedAt") val endedAt: String,
    @SerialName(value = "openedAt") val openedAt: String,
    @SerialName(value = "closedAt") val closedAt: String,
    @SerialName(value = "address") val address: String,
    @SerialName(value = "snsInfo") val snsInfo: Set<SnsInfo>,
    @SerialName(value = "isFavorite") val isFavorite: Boolean,
    @SerialName(value = "isReserved") val isReserved: Boolean
)


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
