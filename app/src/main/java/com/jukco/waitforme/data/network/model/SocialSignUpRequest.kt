package com.jukco.waitforme.data.network.model

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.serialization.SerialName

data class SocialSignUpRequest(
    @SerialName("provider") val provider: String,
    @SerialName("sns_id") val uid: String,
    @SerialName("phoneNumber") val phoneNumber: String? = null,
    @SerialName("isOwner") val isOwner: Boolean? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("birthedAt") val birthedAt: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("isAdult") val isAdult: Boolean = false,
    @SerialName("profileImage") val profileImage: String? = null,
) {
    constructor(googleIdTokenCredential: GoogleIdTokenCredential) : this(
        provider = "google",
        uid = googleIdTokenCredential.id,
        phoneNumber = googleIdTokenCredential.phoneNumber,
        name = googleIdTokenCredential.givenName,
        email = googleIdTokenCredential.id,
        profileImage = googleIdTokenCredential.profilePictureUri.toString(),
    )
}
