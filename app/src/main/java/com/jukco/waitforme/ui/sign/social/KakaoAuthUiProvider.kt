package com.jukco.waitforme.ui.sign.social

import android.content.Context
import android.util.Log
import com.jukco.waitforme.ui.sign.sign_up.SignUpDto
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class KakaoAuthUiProvider(
    private val activityContext: Context,
) : AuthUiProvider {

    override suspend fun signIn(): SignUpDto? = suspendCancellableCoroutine { continuation ->
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
                continuation.resume(null)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공")
                CoroutineScope(Dispatchers.IO).launch {
                    continuation.resume(getUserInfo())
                }
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(activityContext)) {
            UserApiClient.instance.loginWithKakaoTalk(activityContext) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        continuation.resume(null)
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(activityContext, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오계정으로 로그인 성공")
                    CoroutineScope(Dispatchers.IO).launch {
                        continuation.resume(getUserInfo())
                    }
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(activityContext, callback = callback)
        }
    }

    private suspend fun getUserInfo(): SignUpDto? = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
                continuation.resume(null)
            } else if (user != null) {
                continuation.resume(SignUpDto(user))
            }
        }
    }

    companion object {
        private const val TAG = "KakaoAuthUiProvider"
    }
}