package com.jukco.waitforme.ui.sign.sign_in

import com.jukco.waitforme.ui.sign.sign_up.SignUpForm

sealed interface SignInEvent {
    data class InputId(val id: String) : SignInEvent
    data class InputPassword(val password: String) : SignInEvent
    object OnSignInClicked : SignInEvent
    data class OnSocialSignInClicked(val user: SignUpForm?) : SignInEvent
    object MoveMain : SignInEvent
    object MoveSignUp : SignInEvent
}