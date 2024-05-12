package com.jukco.waitforme.ui.sign.sign_up

sealed interface SignUpEvent {
    data class InputPhoneNumber(val phoneNum: String) : SignUpEvent
    object SubmitPhoneNumber : SignUpEvent
    data class InputVerificationCode(val code: String) : SignUpEvent
    object SubmitVerificationCode : SignUpEvent
    data class InputPassword(val password: String) : SignUpEvent
    data class InputConfirmPassword(val confirmPassword: String) : SignUpEvent
    object SubmitCredentials : SignUpEvent
    data class InputName(val name: String) : SignUpEvent
    object CheckDuplicateName : SignUpEvent
    data class ChooseCustomerOrOwner(val choice: Boolean) : SignUpEvent
    data class SignUp(val success: () -> Unit) : SignUpEvent
}