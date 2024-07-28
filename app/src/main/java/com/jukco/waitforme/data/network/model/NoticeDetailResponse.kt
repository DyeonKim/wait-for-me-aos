package com.jukco.waitforme.data.network.model


import com.jukco.waitforme.data.network.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class NoticeDetailResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("contents") val contents: String,
    @Serializable(with = DateTimeSerializer::class) @SerialName("createdDate") val createdDate: LocalDateTime,
)