package com.jukco.waitforme.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("name") val name: String,
    @SerialName("isOwner") val isOwner: Boolean,
    @SerialName("accessToken") val accessToken: Token,
    @SerialName("refreshToken") val refreshToken: Token,
)