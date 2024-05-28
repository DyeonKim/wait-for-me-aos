package com.jukco.waitforme.ui.sign.sign_in

import com.jukco.waitforme.ui.sign.sign_up.SignUpDto

sealed interface SignInEvent {
    data class InputId(val id: String) : SignInEvent
    data class InputPassword(val password: String) : SignInEvent
    data class OnSignInClicked(val success: () -> Unit) : SignInEvent
    data class OnSocialSignInClicked(val user: SignUpDto?, val success: () -> Unit, val register: () -> Unit) : SignInEvent
    object Reset : SignInEvent
}