package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalSignInRequest(
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("password") val password: String,
)

@Serializable
data class SocialSignInRequest(
    @SerialName("provider") val provider: Provider,
    @SerialName("snsId") val snsId: String,
)