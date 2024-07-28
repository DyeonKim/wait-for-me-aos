package com.jukco.waitforme.data.network.model

import com.jukco.waitforme.data.network.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class NoticeResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @Serializable(with = DateTimeSerializer::class) @SerialName("createdAt") val createdAt: LocalDateTime,
)
