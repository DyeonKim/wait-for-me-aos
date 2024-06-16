package com.jukco.waitforme.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoRes(
    @SerialName("provider") val provider: Provider = Provider.LOCAL,
    @SerialName("phoneNumber") val phoneNumber: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("isOwner") val isOwner: Boolean = false,
    @SerialName("birthedAt") val birthedAt: String? = null,
    @SerialName("gender") val genderType: GenderType = GenderType.OTHER,
    @SerialName("profileImage") val profileImage: String? = null,
)