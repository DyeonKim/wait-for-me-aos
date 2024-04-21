package com.jukco.waitforme.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalSignUpRequest(
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("name") val name: String,
    @SerialName("password") val password: String,
    @SerialName("isOwner") val isOwner: Boolean,
)

@Serializable
data class SocialSignUpRequest(
    @SerialName("provider") val provider: Provider,
    @SerialName("sns_id") val uid: String,
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("isOwner") val isOwner: Boolean,
    @SerialName("name") val name: String?,
    @SerialName("birthedAt") val birthedAt: String?,
    @SerialName("gender") val genderType: GenderType,
    @SerialName("profileImage") val profileImage: String?,
)

