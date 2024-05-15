package com.jukco.waitforme.ui.my_info

import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.UserInfoRequest
import com.jukco.waitforme.data.network.model.UserInfoRes

data class UserInfoDto(
    val provider: Provider = Provider.LOCAL,
    val phoneNumber: String = "",
    val name: String = "",
    val isValidNameFormat: Boolean = true,
    val isOwner: Boolean = false,
    val birthedAt: String? = null,
    val genderType: GenderType = GenderType.OTHER,
    val profileImage: String? = null,
    val password: String = "",
    val isValidPasswordFormat: Boolean? = null,
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

fun UserInfoDto.toUserInfoRequest() = UserInfoRequest(
    name = this.name.trim(), // fixme: trim 불필요한지 체크
    birthedAt = this.birthedAt,
    genderType = this.genderType,
    profileImage = this.profileImage,
    password = this.password.trim().ifBlank { null } // fixme: trim 불필요한지 체크
)

