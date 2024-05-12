package com.jukco.waitforme.ui.sign.sign_up

data class SignUpForm(
    val phoneNumber: String = "",
    val phoneNumberSubmitted: Boolean = false,
    val verificationCode: String = "",
    val verificationCodeSubmitted: Boolean = false,
    val password: String = "",
    val passwordVerification: Boolean? = null,
    val confirmPassword: String = "",
    val passwordSubmitted: Boolean = false,
    val name: String = "",
    val nameSubmitted: Boolean = false,
)
