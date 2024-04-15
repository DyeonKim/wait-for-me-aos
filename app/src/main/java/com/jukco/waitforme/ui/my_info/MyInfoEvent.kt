package com.jukco.waitforme.ui.my_info

import com.jukco.waitforme.data.network.model.GenderType

sealed interface MyInfoEvent {
    data class InputName(val name: String) : MyInfoEvent
    object CheckDuplicateName : MyInfoEvent
    data class InputBirthDay(val birth: String) : MyInfoEvent
    data class SelectGender(val genderType: GenderType) : MyInfoEvent
    data class InputProfileImage(val profileUri: String) : MyInfoEvent
    data class InputPassword(val password: String) : MyInfoEvent
    data class InputConfirmPassword(val confirmPassword: String) : MyInfoEvent
    object Edit : MyInfoEvent
    object Save : MyInfoEvent
    object Cancel : MyInfoEvent
}