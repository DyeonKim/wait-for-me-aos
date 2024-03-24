package com.jukco.waitforme.data.network.model

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.kakao.sdk.user.model.User
import com.navercorp.nid.profile.data.NidProfile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SocialSignUpRequest(
    @SerialName("provider") val provider: String,
    @SerialName("sns_id") val uid: String,
    @SerialName("phoneNumber") val phoneNumber: String? = null,
    @SerialName("isOwner") val isOwner: Boolean? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("birthedAt") val birthedAt: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("isAdult") val isAdult: Boolean = false,
    @SerialName("profileImage") val profileImage: String? = null,
)


