package com.jukco.waitforme.ui.sign.social

import com.jukco.waitforme.ui.sign.sign_up.SignUpForm

interface AuthUiProvider {
    suspend fun signIn(): SignUpForm?
}

object MockAuthUiProvider : AuthUiProvider {
    override suspend fun signIn(): SignUpForm? = null
}