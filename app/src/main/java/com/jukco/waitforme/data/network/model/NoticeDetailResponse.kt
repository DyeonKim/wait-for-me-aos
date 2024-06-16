package com.jukco.waitforme.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoticeDetailResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("contents") val contents: String,
    @SerialName("createdDate") val createdDate: String,
)