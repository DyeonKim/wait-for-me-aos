package com.jukco.waitforme.ui.sign.social

import android.content.Context
import android.util.Log
import com.jukco.waitforme.ui.sign.sign_up.SignUpDto
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class NaverAuthUiProvider(
    private val activityContext: Context,
) : AuthUiProvider {
    override suspend fun signIn(): SignUpDto? = suspendCancellableCoroutine { continuation ->
        val oAuthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                CoroutineScope(Dispatchers.IO).launch {
                    continuation.resume(getUserInfo())
                }
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.e(TAG, "Login Fail: errorCode-$errorCode, errorDesc-$errorDescription")
                continuation.resume(null)
            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        NaverIdLoginSDK.authenticate(activityContext, oAuthLoginCallback)
    }

    private suspend fun getUserInfo(): SignUpDto? = suspendCancellableCoroutine { continuation ->
        val nidProfileCallback = object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(result: NidProfileResponse) {
                val profile = result.profile
                if (profile != null) {
                    continuation.resume(SignUpDto(profile))
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