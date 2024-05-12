package com.jukco.waitforme.ui.sign.sign_in

import com.jukco.waitforme.ui.navi.Route

sealed interface SignInState {
    object Init : SignInState
    object Loading : SignInState
    data class Move(val route: Route) : SignInState
}