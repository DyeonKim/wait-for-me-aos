package com.jukco.waitforme.ui.my_info

import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.UserInfoRes

data class UserInfo(
    val provider: Provider = Provider.LOCAL,
    val phoneNumber: String = "",
    val name: String = "",
    val nameSubmitted: Boolean = true,
    val isOwner: Boolean = false,
    val birthedAt: String? = null,
    val genderType: GenderType = GenderType.OTHER,
    val profileImage: String? = null,
    val password: String = "",
    val confirmPassword: String = "",
    val passwordSubmitted: Boolean? = null,
) {
    constructor(userInfoRes: UserInfoRes) : this(
        provider = userInfoRes.provider,
        phoneNumber = userInfoRes.phoneNumber,
        name = userInfoRes.name,
        isOwner = userInfoRes.isOwner,
        birthedAt = userInfoRes.birthedAt,
        genderType = userInfoRes.genderType,
        profileImage = userInfoRes.profileImage,
    )
}

