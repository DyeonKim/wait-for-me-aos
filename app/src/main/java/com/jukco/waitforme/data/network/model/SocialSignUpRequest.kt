package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SocialSignUpRequest(
    @SerialName("provider") val provider: Provider,
    @SerialName("sns_id") val uid: String,
    @SerialName("phoneNumber") val phoneNumber: String? = null,
    @SerialName("isOwner") val isOwner: Boolean? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("birthedAt") val birthedAt: String? = null,
    @SerialName("gender") val genderType: GenderType = GenderType.OTHER,
    @SerialName("isAdult") val isAdult: Boolean = false,
    @SerialName("profileImage") val profileImage: String? = null,
)


