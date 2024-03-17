package com.jukco.waitforme.data.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.jukco.waitforme.ui.sign.social.AuthUiProvider
import com.jukco.waitforme.ui.sign.social.NaverAuthUiProvider
import com.navercorp.nid.NaverIdLoginSDK

class NaverAuthProvider : AuthProvider {
    @Composable
    override fun getUiProvider(): AuthUiProvider {
        val activityContext = LocalContext.current
        return NaverAuthUiProvider(activityContext)
    }

    override suspend fun signOut() {
        NaverIdLoginSDK.logout()
    }
}