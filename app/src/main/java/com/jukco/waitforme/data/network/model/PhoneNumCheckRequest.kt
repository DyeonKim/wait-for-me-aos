package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneNumCheckRequest(
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("authText") val authnNum: String,
)
