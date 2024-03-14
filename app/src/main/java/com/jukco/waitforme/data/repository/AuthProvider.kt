package com.jukco.waitforme.data.repository

import androidx.compose.runtime.Composable
import com.jukco.waitforme.ui.sign.AuthUiProvider
import com.jukco.waitforme.ui.sign.MockAuthUiProvider

interface AuthProvider {
    @Composable
    fun getUiProvider(): AuthUiProvider

    suspend fun signOut()
}

object MockAuthProvider : AuthProvider {
    @Composable
    override fun getUiProvider(): AuthUiProvider = MockAuthUiProvider

    override suspend fun signOut() = Unit
}