package com.jukco.waitforme.ui.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.jukco.waitforme.data.network.model.SocialSignUpRequest
import com.jukco.waitforme.data.repository.AuthProvider
import kotlinx.coroutines.launch

interface SocialSignContainerScope {
    fun onClick()
}

@Composable
fun SocialSignContainer(
    authProvider: AuthProvider,
    onSignInResult: (SocialSignUpRequest?) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable SocialSignContainerScope.() -> Unit,
) {
    val authUiProvider = authProvider.getUiProvider()
    val coroutineScope = rememberCoroutineScope()
    val containerScope = remember {
        object : SocialSignContainerScope {
            override fun onClick() {
                coroutineScope.launch {
                    val user = authUiProvider.signIn()
                    onSignInResult(user)
                }
            }
        }
    }
    Surface(
        modifier = modifier,
        content = { containerScope.content() },
    )
}