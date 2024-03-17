package com.jukco.waitforme.ui.sign.sign_in

sealed interface SignInState {
    object Init : SignInState
    object Loading : SignInState
    object MovingMain : SignInState
}