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
import com.jukco.waitforme.data.network.model.SocialSignUpRequest
import com.jukco.waitforme.data.repository.AuthProvider
import com.jukco.waitforme.data.repository.SignRepository
import com.jukco.waitforme.ui.util.ValidationChecker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface SignInState {
    object Init : SignInState
    object Success : SignInState
}
sealed interface SignInEvent {
    data class InputId(val id: String) : SignInEvent
    data class InputPassword(val password: String) : SignInEvent
    object OnSignInClicked : SignInEvent
    data class OnSocialSignInClicked(val user: SocialSignUpRequest?) : SignInEvent
}

class SignInViewModel(
    private val signRepository: SignRepository,
    private val googleAuthProvider: AuthProvider,
) : ViewModel() {
    var signInState by mutableStateOf<SignInState>(SignInState.Init)
        private set
    var isLoading by mutableStateOf(false)
        private set
    var form by mutableStateOf(SignInForm())
        private set
    private val _socialSignUpRequest = MutableStateFlow<SocialSignUpRequest?>(null)

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.InputId -> { inputId(event.id) }
            is SignInEvent.InputPassword -> { inputPassword(event.password) }
            is SignInEvent.OnSignInClicked -> { onSignInClicked() }
            is SignInEvent.OnSocialSignInClicked -> { onSocialSignInClicked(event.user) }
        }
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
    fun getSocialSign(service: SocialService) =
        when (service) {
            is SocialService.Google -> { googleAuthProvider }
        }

    private fun onSocialSignInClicked(user: SocialSignUpRequest?) {
        if (user != null) {
            _socialSignUpRequest.update { user }
            socialSignIn(user.uid)
        }
    }

    private fun checkId(): Boolean = ValidationChecker.checkIdValidation(form.id).first

    private fun checkPassword() = ValidationChecker.checkPasswordValidation(form.password).first

    private fun localSignIn(id: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val phoneNumAndPassword = signRepository.convertLocalSignInBody(id, password)
                val response = signRepository.localSignIn(phoneNumAndPassword)
                if (response.isSuccessful) {
                    // TODO : DataStore에 결과 저장
                    signInState = SignInState.Success
                } else {
                    // TODO
                }
            } catch (e: IOException) {
                // TODO
            } finally {
                isLoading = false
            }
        }
    }

    private fun socialSignIn(uid: String) {
        viewModelScope.launch {
            isLoading = true
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val response = signRepository.socialSignIn(uid)
                if (response.isSuccessful) {
                    // TODO : DataStore에 결과 저장
                    signInState = SignInState.Success
                } else {
                    // TODO : 가입되어 있지 않음. 회원가입으로, 혹은 다른 오류 처리
                }
            } catch (e: IOException) {
                // TODO
            } finally {
                isLoading = false
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationClass)
                val signRepository = application.container.signRepository
                val googleAuthProvider = application.container.googleAuthProvider
                SignInViewModel(signRepository, googleAuthProvider)
            }
        }
    }
}
