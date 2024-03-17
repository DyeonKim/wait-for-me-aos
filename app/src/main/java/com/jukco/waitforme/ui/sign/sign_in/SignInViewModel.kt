package com.jukco.waitforme.ui.sign.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.LocalSignInRequest
import com.jukco.waitforme.data.network.model.SocialSignInRequest
import com.jukco.waitforme.data.repository.AuthProvider
import com.jukco.waitforme.data.repository.SignRepository
import com.jukco.waitforme.ui.sign.sign_up.SignUpForm
import com.jukco.waitforme.ui.util.ValidationChecker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class SignInViewModel(
    private val signRepository: SignRepository,
    private val googleAuthProvider: AuthProvider,
    private val kakaoAuthProvider: AuthProvider,
    private val naverAuthProvider: AuthProvider,
) : ViewModel() {
    var signInState by mutableStateOf<SignInState>(SignInState.Init)
        private set
    var form by mutableStateOf(SignInForm())
        private set

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.InputId -> { inputId(event.id) }
            is SignInEvent.InputPassword -> { inputPassword(event.password) }
            is SignInEvent.OnSignInClicked -> { onSignInClicked() }
            is SignInEvent.OnSocialSignInClicked -> { onSocialSignInClicked(event.user) }
        }
    }

    fun getSocialSign(service: SocialService) =
        when (service) {
            is SocialService.Google -> { googleAuthProvider }
            is SocialService.Kakao -> { kakaoAuthProvider }
            is SocialService.Naver -> { naverAuthProvider }
        }

    private fun inputId(input: String) {
        form = form.copy(id = input)
    }

    private fun inputPassword(input: String) {
        form = form.copy(password = input)
    }

    private fun onSignInClicked() {
        if (checkId() and checkPassword()) {
            form = form.copy(hasError = false)
            localSignIn(form.id, form.password)
        } else {
            form = form.copy(hasError = true)
        }
    }

    private fun onSocialSignInClicked(user: SignUpForm?) {
        if (user != null) {
            socialSignIn(user.provider, user.snsId)
        }
    }

    private fun checkId(): Boolean = ValidationChecker.checkIdValidation(form.id).first

    private fun checkPassword() = ValidationChecker.checkPasswordValidation(form.password).first

    private fun localSignIn(id: String, password: String) {
        viewModelScope.launch {
            signInState = SignInState.Loading
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val request = LocalSignInRequest(id, password)
                val response = signRepository.localSignIn(request)
                if (response.isSuccessful) {
                    // TODO : DataStore에 결과 저장
                    signInState = SignInState.MovingMain
                    return@launch
                }
                // TODO
                signInState = SignInState.Init
            } catch (e: IOException) {
                // TODO
                signInState = SignInState.Init
            }
        }
    }

    private fun socialSignIn(provider: String, snsId: String) {
        viewModelScope.launch {
            signInState = SignInState.Loading
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val request = SocialSignInRequest(provider, snsId)
                val response = signRepository.socialSignIn(request)
                if (response.isSuccessful) {
                    when(response.code()) {
                        200 -> {
                            // TODO : DataStore에 결과 저장
                            signInState = SignInState.MovingMain
                            return@launch
                        }
                        204 -> {
                            // TODO : 회원가입
                        }
                    }
                }
                // TODO
                signInState = SignInState.Init
            } catch (e: IOException) {
                // TODO
                signInState = SignInState.Init
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationClass)
                val signRepository = application.container.signRepository
                val googleAuthProvider = application.container.googleAuthProvider
                val kakaoAuthProvider = application.container.kakaoAuthProvider
                val naverAuthProvider = application.container.naverAuthProvider

                SignInViewModel(signRepository, googleAuthProvider, kakaoAuthProvider, naverAuthProvider)
            }
        }
    }
}
