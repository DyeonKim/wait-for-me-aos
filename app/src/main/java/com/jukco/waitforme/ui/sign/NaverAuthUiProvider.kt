package com.jukco.waitforme.ui.sign

import android.content.Context
import android.util.Log
import com.jukco.waitforme.data.network.model.SocialSignUpRequest
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class NaverAuthUiProvider(
    private val activityContext: Context,
) : AuthUiProvider {
    override suspend fun signIn(): SocialSignUpRequest? =
        if (signInAndCheckSuccess()) {
            getUserInfo()
        } else {
            null
        }

    private suspend fun signInAndCheckSuccess(): Boolean = suspendCancellableCoroutine { continuation ->
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
                continuation.resume(true)
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.e(TAG, "Login Fail: errorCode-$errorCode, errorDesc-$errorDescription")
                continuation.resume(false)
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        NaverIdLoginSDK.authenticate(activityContext, oauthLoginCallback)
    }

    private suspend fun getUserInfo(): SocialSignUpRequest? = suspendCancellableCoroutine { continuation ->
        val nidProfileCallback = object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(response: NidProfileResponse) {
                val profile = response.profile
                if (profile != null) {
                    continuation.resume(SocialSignUpRequest(profile))
                } else {
                    Log.e(TAG, "사용자 정보 요청 실패")
                    continuation.resume(null)
                }
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.e(TAG, "사용자 정보 요청 실패: errorCode-$errorCode, errorDesc-$errorDescription")
                continuation.resume(null)
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        NidOAuthLogin().callProfileApi(nidProfileCallback)
    }

    companion object {
        private const val TAG = "NaverAuthUiProvider"
    }
}