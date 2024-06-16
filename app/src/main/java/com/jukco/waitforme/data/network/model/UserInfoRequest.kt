package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoRequest(
    @SerialName("name") val name: String = "",
    @SerialName("birthedAt") val birthedAt: String? = null,
    @SerialName("gender") val genderType: GenderType = GenderType.OTHER,
    @SerialName("profileImage") val profileImage: String? = null,
    @SerialName("password") val password: String? = null,
)
