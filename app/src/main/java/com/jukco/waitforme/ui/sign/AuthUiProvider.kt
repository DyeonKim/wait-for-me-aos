package com.jukco.waitforme.ui.sign

import com.jukco.waitforme.data.network.model.SocialSignUpRequest

interface AuthUiProvider {
    suspend fun signIn(): SocialSignUpRequest?
}

object MockAuthUiProvider : AuthUiProvider {
    override suspend fun signIn(): SocialSignUpRequest? = null
}