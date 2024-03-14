package com.jukco.waitforme.data.repository

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.jukco.waitforme.ui.sign.AuthUiProvider
import com.jukco.waitforme.ui.sign.KakaoAuthUiProvider
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class KakaoAuthProvider : AuthProvider {
    @Composable
    override fun getUiProvider(): AuthUiProvider {
        val activityContext = LocalContext.current
        return KakaoAuthUiProvider(activityContext)
    }

    override suspend fun signOut() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            } else {
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }
    }

    companion object {
        private const val TAG = "KakaoAuthProvider"
    }
}