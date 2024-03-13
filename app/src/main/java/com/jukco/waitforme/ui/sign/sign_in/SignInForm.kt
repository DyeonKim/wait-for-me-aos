package com.jukco.waitforme.ui.sign.sign_in

data class SignInForm(
    val id: String = "",
    val password: String = "",
    val hasError: Boolean? = null,
)
