package com.jukco.waitforme.data.network.model

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.kakao.sdk.user.model.User
import com.navercorp.nid.profile.data.NidProfile
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

    constructor(kakaoUser: User) : this(
        provider = "kakao",
        uid = kakaoUser.id.toString(),
        phoneNumber = kakaoUser.kakaoAccount?.phoneNumber,
        name = kakaoUser.kakaoAccount?.profile?.nickname,
        email = kakaoUser.kakaoAccount?.email,
        profileImage = kakaoUser.kakaoAccount?.profile?.thumbnailImageUrl,
    )

    constructor(nidProfile: NidProfile) : this(
        provider = "naver",
        uid = nidProfile.id!!,
        phoneNumber = nidProfile.mobile?.replace("-", ""),
        name = nidProfile.nickname,
        email = nidProfile.email,
        birthedAt = convertBirthedAt(nidProfile.birthYear, nidProfile.birthday),
        gender = if (nidProfile.gender == "F") "FEMALE" else "MALE",
        profileImage = nidProfile.profileImage,
    )
}

private fun convertBirthedAt(year: String?, monthDay: String?) =
    if (year != null && monthDay != null) {
        "$year-$monthDay"
    } else {
        null
    }
