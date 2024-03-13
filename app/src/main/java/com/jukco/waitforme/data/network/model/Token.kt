package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Token(
    @SerialName("token") val token: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("expiredAt") val expiredAt: String,
)