package com.jukco.waitforme.ui.sign.social

import com.jukco.waitforme.ui.sign.sign_up.SignUpDto

interface AuthUiProvider {
    suspend fun signIn(): SignUpDto?
}

object MockAuthUiProvider : AuthUiProvider {
    override suspend fun signIn(): SignUpDto? = null
}