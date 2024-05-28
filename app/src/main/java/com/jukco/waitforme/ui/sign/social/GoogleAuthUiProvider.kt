package com.jukco.waitforme.ui.sign.social

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.jukco.waitforme.ui.sign.sign_up.SignUpDto

class GoogleAuthUiProvider(
    private val activityContext: Context,
    private val credentialManager: CredentialManager,
    private val serverClientId: String,
) : AuthUiProvider {
    override suspend fun signIn(): SignUpDto? {
        return try {
            val credential = credentialManager.getCredential(
                context = activityContext,
                request = getCredentialRequest(true),
            ).credential
            getGoogleUserFromCredential(credential)
        } catch (e: GetCredentialException) {
            Log.w(TAG, "승인된 계정이 없습니다.")
            signUp()
        }
    }

    private suspend fun signUp(): SignUpDto? {
        return try {
            val credential = credentialManager.getCredential(
                context = activityContext,
                request = getCredentialRequest(false),
            ).credential
            getGoogleUserFromCredential(credential)
        } catch (e: GetCredentialException) {
            Log.e(TAG, "구글 로그인 실패", e)
            null
        }
    }

    private fun getGoogleUserFromCredential(credential: Credential): SignUpDto? {
        return when {
            credential is CustomCredential &&
                    credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                try {
                    val googleIdTokenCredential = GoogleIdTokenCredential
                        .createFrom(credential.data)
                    SignUpDto(googleIdTokenCredential)
                } catch (e: GoogleIdTokenParsingException) {
                    Log.e(TAG, "Received an invalid google id token response", e)
                    null
                }
            }
            else -> {
                Log.e(TAG, "구글 로그인 실패: Unexpected type of credential")
                null
            }
        }
    }

    private fun getCredentialRequest(isAuthorized: Boolean): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(getGoogleIdOption(isAuthorized))
            .build()
    }

    private fun getGoogleIdOption(isAuthorized: Boolean): GetGoogleIdOption {
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(isAuthorized)
            .setServerClientId(serverClientId)
            .build()
    }

    companion object {
        private const val TAG = "GoogleAuthUiProvider"
    }
}
