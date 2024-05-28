package com.jukco.waitforme.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.SignInResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime


val Context.signInData: DataStore<Preferences> by preferencesDataStore(name = "sign_in_data")

interface TokenManager {
    val providerFlow: Flow<String>
    val userNameFlow: Flow<String>
    val userPhoneNumberFlow: Flow<String>
    val isOwnerFlow: Flow<Boolean>
    val accessTokenFlow: Flow<String>
    val accessTokenCreatedTimeFlow: Flow<LocalDateTime?>
    val accessTokenExpiredTimeFlow: Flow<LocalDateTime?>
    val refreshTokenFlow: Flow<String>
    val refreshTokenCreatedTimeFlow: Flow<LocalDateTime?>
    val refreshTokenExpiredTimeFlow: Flow<LocalDateTime?>

    suspend fun saveToken(provider: Provider, signInResponse: SignInResponse)
}

class TokenManagerImplementation (
    private val dataStore: DataStore<Preferences>
) : TokenManager {
    override val providerFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[PROVIDER_KEY] ?: Provider.LOCAL.name
    }
    override val userNameFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: ""
    }
    override val userPhoneNumberFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[USER_PHONE_NUMBER_KEY] ?: ""
    }
    override val isOwnerFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_OWNER_KEY] ?: false
    }
    override val accessTokenFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN_KEY] ?: ""
    }
    override val accessTokenCreatedTimeFlow: Flow<LocalDateTime?> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN_CREATED_TIME_KEY]?.let {
            LocalDateTime.parse(it)
        }
    }
    override val accessTokenExpiredTimeFlow: Flow<LocalDateTime?> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN_EXPIRED_TIME_KEY]?.let {
            LocalDateTime.parse(it)
        }
    }
    override val refreshTokenFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN_KEY] ?: ""
    }
    override val refreshTokenCreatedTimeFlow: Flow<LocalDateTime?> = dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN_CREATED_TIME_KEY]?.let {
            LocalDateTime.parse(it)
        }
    }
    override val refreshTokenExpiredTimeFlow: Flow<LocalDateTime?> = dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN_EXPIRED_TIME_KEY]?.let {
            LocalDateTime.parse(it)
        }
    }

    override suspend fun saveToken(provider: Provider, signInResponse: SignInResponse) {
        dataStore.edit { signInData ->
            signInData[PROVIDER_KEY] = provider.name
            signInData[USER_NAME_KEY] = signInResponse.name
            signInData[USER_PHONE_NUMBER_KEY] = signInResponse.name
            signInData[IS_OWNER_KEY] = signInResponse.isOwner

            signInData[ACCESS_TOKEN_KEY] = signInResponse.accessToken.token
            signInData[ACCESS_TOKEN_CREATED_TIME_KEY] = signInResponse.accessToken.createdAt
            signInData[ACCESS_TOKEN_EXPIRED_TIME_KEY] = signInResponse.accessToken.expiredAt

            signInData[REFRESH_TOKEN_KEY] = signInResponse.refreshToken.token
            signInData[REFRESH_TOKEN_CREATED_TIME_KEY] = signInResponse.refreshToken.createdAt
            signInData[REFRESH_TOKEN_EXPIRED_TIME_KEY] = signInResponse.refreshToken.expiredAt
        }
    }

    companion object {
        val PROVIDER_KEY = stringPreferencesKey("PROVIDER")
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
        val USER_PHONE_NUMBER_KEY = stringPreferencesKey("USER_PHONE_NUMBER")
        val IS_OWNER_KEY = booleanPreferencesKey("IS_OWNER")
        val ACCESS_TOKEN_KEY = stringPreferencesKey("ACCESS_TOKEN")
        val ACCESS_TOKEN_CREATED_TIME_KEY = stringPreferencesKey("ACCESS_TOKEN_CREATED_TIME")
        val ACCESS_TOKEN_EXPIRED_TIME_KEY = stringPreferencesKey("ACCESS_TOKEN_EXPIRED_TIME")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("REFRESH_TOKEN")
        val REFRESH_TOKEN_CREATED_TIME_KEY = stringPreferencesKey("REFRESH_TOKEN_CREATED_TIME")
        val REFRESH_TOKEN_EXPIRED_TIME_KEY = stringPreferencesKey("REFRESH_TOKEN_EXPIRED_TIME")
    }
}