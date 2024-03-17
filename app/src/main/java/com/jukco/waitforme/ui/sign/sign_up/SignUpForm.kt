package com.jukco.waitforme.ui.sign.sign_up

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.kakao.sdk.user.model.User
import com.navercorp.nid.profile.data.NidProfile
data class SignUpForm(
    val provider: String,
    val snsId: String,
    val phoneNumber: String? = null,
    val isOwner: Boolean? = null,
    val name: String? = null,
    val birthedAt: String? = null,
    val gender: String? = null,
    val profileImage: String? = null,
) {
    constructor(googleIdTokenCredential: GoogleIdTokenCredential) : this(
        provider = "GOOGLE",
        snsId = googleIdTokenCredential.id,
        phoneNumber = googleIdTokenCredential.phoneNumber,
        name = googleIdTokenCredential.givenName,
        profileImage = googleIdTokenCredential.profilePictureUri.toString(),
    )

    constructor(kakaoUser: User) : this(
        provider = "KAKAO",
        snsId = kakaoUser.id.toString(),
        phoneNumber = kakaoUser.kakaoAccount?.phoneNumber,
        name = kakaoUser.kakaoAccount?.profile?.nickname,
        birthedAt = convertBirthedAt(kakaoUser.kakaoAccount?.birthyear, kakaoUser.kakaoAccount?.birthday),
        gender = kakaoUser.kakaoAccount?.gender.toString(),
        profileImage = kakaoUser.kakaoAccount?.profile?.thumbnailImageUrl,
    )

    constructor(nidProfile: NidProfile) : this(
        provider = "NAVAER",
        snsId = nidProfile.id!!,
        phoneNumber = nidProfile.mobile?.replace("-", ""),
        name = nidProfile.nickname,
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
