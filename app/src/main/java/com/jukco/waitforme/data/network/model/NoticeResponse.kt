package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoticeResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("createdAt") val createdAt: String,
)
