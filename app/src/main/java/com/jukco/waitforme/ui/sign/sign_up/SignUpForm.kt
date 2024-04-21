package com.jukco.waitforme.ui.sign.sign_up

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.data.network.model.Provider
import com.kakao.sdk.user.model.Gender
import com.kakao.sdk.user.model.User
import com.navercorp.nid.profile.data.NidProfile

data class SignUpForm(
    val provider: Provider = Provider.LOCAL,
    val snsId: String = "",
    val phoneNumber: String = "",
    val phoneNumberSubmitted: Boolean = false,
    val verificationCode: String = "",
    val verificationCodeSubmitted: Boolean = false,
    val password: String = "",
    val passwordVerification: Boolean? = null,
    val confirmPassword: String = "",
    val passwordSubmitted: Boolean = false,
    val isOwner: Boolean = false,
    val name: String = "",
    val nameSubmitted: Boolean = false,
    val birthedAt: String? = null,
    val genderType: GenderType = GenderType.OTHER,
    val profileImage: String? = null,
) {
    constructor(googleIdTokenCredential: GoogleIdTokenCredential) : this(
        provider = Provider.GOOGLE,
        snsId = googleIdTokenCredential.id,
        phoneNumber = googleIdTokenCredential.phoneNumber ?: "",
        name = googleIdTokenCredential.givenName ?: "",
        profileImage = googleIdTokenCredential.profilePictureUri.toString(),
    )

    constructor(kakaoUser: User) : this(
        provider = Provider.KAKAO,
        snsId = kakaoUser.id.toString(),
        phoneNumber = kakaoUser.kakaoAccount?.phoneNumber ?: "",
        name = kakaoUser.kakaoAccount?.profile?.nickname ?: "",
        birthedAt = convertBirthedAt(kakaoUser.kakaoAccount?.birthyear, kakaoUser.kakaoAccount?.birthday),
        genderType = when (kakaoUser.kakaoAccount?.gender) {
            Gender.FEMALE -> GenderType.FEMALE
            Gender.MALE -> GenderType.MALE
            else -> GenderType.OTHER
        }
        ,
        profileImage = kakaoUser.kakaoAccount?.profile?.thumbnailImageUrl,
    )

    constructor(nidProfile: NidProfile) : this(
        provider = Provider.NAVER,
        snsId = nidProfile.id!!,
        phoneNumber = nidProfile.mobile?.replace("-", "") ?: "",
        name = nidProfile.nickname ?: "",
        birthedAt = convertBirthedAt(nidProfile.birthYear, nidProfile.birthday),
        genderType = when (nidProfile.gender) {
            "F" -> GenderType.FEMALE
            "M" -> GenderType.MALE
            else -> GenderType.OTHER
        }
        ,
        profileImage = nidProfile.profileImage,
    )
}

private fun convertBirthedAt(year: String?, monthDay: String?) =
    if (year != null && monthDay != null) {
        "$year-${monthDay.replace(Regex("^\\d"), "-")}"
    } else {
        null
    }
