package com.jukco.waitforme.ui.sign

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jukco.waitforme.R
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.LocalSignInRequest
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.SocialSignInRequest
import com.jukco.waitforme.data.repository.AuthProvider
import com.jukco.waitforme.data.repository.SignRepository
import com.jukco.waitforme.ui.sign.sign_in.SignInEvent
import com.jukco.waitforme.ui.sign.sign_in.SignInForm
import com.jukco.waitforme.ui.sign.sign_in.SocialService
import com.jukco.waitforme.ui.sign.sign_up.SignUpForm
import com.jukco.waitforme.ui.sign.sign_up.CustomerOwner
import com.jukco.waitforme.ui.sign.sign_up.SignUpEvent
import com.jukco.waitforme.ui.sign.sign_up.SignUpDto
import com.jukco.waitforme.ui.sign.sign_up.toLocalReq
import com.jukco.waitforme.ui.sign.sign_up.toSocialReq
import com.jukco.waitforme.ui.util.ValidationChecker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class SignViewModel(
    private val signRepository: SignRepository,
    private val googleAuthProvider: AuthProvider,
    private val kakaoAuthProvider: AuthProvider,
    private val naverAuthProvider: AuthProvider,
) : ViewModel() {
    var signInform by mutableStateOf(SignInForm())
        private set
    var signUpDto by mutableStateOf(SignUpDto())
        private set
    var signUpForm by mutableStateOf(SignUpForm())
        private set
    var errorMessage by mutableStateOf<Int?>(null)
        private set
    var isLoading by mutableStateOf(false)
        private set


    fun onSignInEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.InputId -> { signInform = signInform.copy(id = event.id) }
            is SignInEvent.InputPassword -> { signInform = signInform.copy(password = event.password) }
            is SignInEvent.OnSignInClicked -> { onSignInClicked(event.success) }
            is SignInEvent.OnSocialSignInClicked -> { onSocialSignInClicked(event.user, event.success, event.register) }
            is SignInEvent.Reset -> { reset() }
        }
    }

    fun onSignUpEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.InputPhoneNumber -> { signUpForm = signUpForm.copy(phoneNumber = event.phoneNum) }
            is SignUpEvent.SubmitPhoneNumber -> {
                signUpForm = signUpForm.copy(phoneNumberSubmitted = checkId(signUpForm.phoneNumber))
            }
            is SignUpEvent.InputVerificationCode -> { signUpForm = signUpForm.copy(verificationCode = event.code) }
            is SignUpEvent.SubmitVerificationCode -> { signUpForm = signUpForm.copy(verificationCodeSubmitted = true) }
            is SignUpEvent.InputPassword -> {
                signUpForm = signUpForm.copy(
                    password = event.password,
                    passwordVerification = checkPassword(event.password),
                )
            }
            is SignUpEvent.InputConfirmPassword -> {
                val passwordConfirmed = (signUpForm.password == event.confirmPassword)

                signUpForm = signUpForm.copy(
                    confirmPassword = event.confirmPassword,
                    passwordSubmitted = (signUpForm.passwordVerification == true) && passwordConfirmed,
                )
                errorMessage = if (passwordConfirmed) null else R.string.error_password_confirm
            }
            is SignUpEvent.SubmitCredentials -> { signUpDto = signUpDto.copy(phoneNumber = signUpForm.phoneNumber) }
            is SignUpEvent.InputName -> {
                signUpForm = signUpForm.copy(
                    name = event.name,
                    nameSubmitted = checkName(event.name),
                )
            }
            is SignUpEvent.CheckDuplicateName -> { checkDuplicateName() }
            is SignUpEvent.ChooseCustomerOrOwner -> { signUpDto = signUpDto.copy(isOwner = event.choice) }
            is SignUpEvent.SignUp -> { onSignUpClicked(event.success, { /*TODO : Fail */ }) }
        }
    }

    fun getSocialSign(service: SocialService) =
        when (service) {
            is SocialService.Google -> { googleAuthProvider }
            is SocialService.Kakao -> { kakaoAuthProvider }
            is SocialService.Naver -> { naverAuthProvider }
        }

    private fun reset() {
        signInform = SignInForm()
        signUpDto = SignUpDto()
        errorMessage = null
    }

    private fun onSignInClicked(success: () -> Unit) {
        if (checkId(signInform.id) and checkPassword(signInform.password)) {
            signInform = signInform.copy(hasError = false)
            localSignIn(signInform.id, signInform.password, success)
        } else {
            signInform = signInform.copy(hasError = true)
        }
    }

    private fun onSocialSignInClicked(user: SignUpDto?, success: () -> Unit, register: () -> Unit) {
        if (user != null) {
            signUpDto = user
            socialSignIn(user.provider, user.snsId, success, register)
        }
    }

    private fun onSignUpClicked(success: () -> Unit, fail: () -> Unit) {
        if (signUpDto.provider == Provider.LOCAL) {
            localSignUp(success, fail)
        } else {
            socialSignUp(success, fail)
        }
    }

    private fun localSignIn(id: String, password: String, success: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val request = LocalSignInRequest(id, password)
                val response = signRepository.localSignIn(request)
                if (response.isSuccessful) {
                    // TODO : DataStore에 결과 저장
                    success()
                    return@launch
                }
                // TODO : 30X, 40X 오류 처리
            } catch (e: IOException) {
                // TODO : 50X 오류 처리
            } finally {
                isLoading = false
            }
        }
    }

    private fun socialSignIn(provider: Provider, snsId: String, success: () -> Unit, register: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val request = SocialSignInRequest(provider, snsId)
                val response = signRepository.socialSignIn(request)
                if (response.isSuccessful) {
                    when (response.code()) {
                        200 -> {
                            // TODO : DataStore에 결과 저장
                            success()
                        }
                        204 -> { register() }
                    }
                    return@launch
                }
                // TODO : 30X, 40X 오류 처리
            } catch (e: IOException) {
                // TODO : 50X 오류 처리
            } finally {
                isLoading = false
            }
        }
    }

    private fun localSignUp(success: () -> Unit, fail: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val request = signUpDto.toLocalReq()
                val response = signRepository.localSignUp(request)
                if (response.isSuccessful) {
                    // TODO : DataStore에 결과 저장
                    success()
                    return@launch
                }
                // TODO : 30X, 40X 오류 처리
                fail()
            } catch (e: IOException) {
                // TODO : 50X 오류 처리
                fail()
            } finally {
                isLoading = false
            }
        }
    }

    private fun socialSignUp(success: () -> Unit, fail: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val request = signUpDto.toSocialReq()
                val response = signRepository.socialSignUp(request)
                if (response.isSuccessful) {
                    // TODO : DataStore에 결과 저장
                    success()
                    return@launch
                }
                // TODO : 30X, 40X 오류 처리
                fail()
            } catch (e: IOException) {
                // TODO : 50X 오류 처리
                fail()
            } finally {
                isLoading = false
            }
        }
    }

    private fun checkDuplicateName() {
        viewModelScope.launch {
            isLoading = true
            delay(3000) // TODO : 서버와 연결 후에는 지울 것. 기다리는 최대 시간이 있어야 한다.

            try {
                val response = signRepository.checkDuplicateName(signUpForm.name)
                if (response.isSuccessful) {
                    response.body()?.also { isUnique ->
                        if (isUnique) {
                            errorMessage = null
                            signUpDto = signUpDto.copy(name = signUpForm.name)
                        } else {
                            errorMessage = R.string.error_nickname_duplicate
                            signUpForm = signUpForm.copy(
                                nameSubmitted = false,
                            )
                        }
                    }
                    return@launch
                }
                // TODO : 30X, 40X 오류 처리
            } catch (e: IOException) {
                // TODO : 50X 오류 처리
            } finally {
                isLoading = false
            }
        }
    }

    private fun checkId(id: String): Boolean =
        ValidationChecker.checkIdValidation(id).apply {
            errorMessage = second
        }.first

    private fun checkPassword(password: String) =
        ValidationChecker.checkPasswordValidation(password).apply {
            errorMessage = second
        }.first

    private fun checkName(name: String) = ValidationChecker.checkNameValidation(name).apply {
        errorMessage = second
    }.first

    companion object {
        val CUSTOMER_OWNER = arrayOf(
            CustomerOwner(R.string.customer_title, R.string.customer_description, false),
            CustomerOwner(R.string.owner_title, R.string.owner_description, true),
        )

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationClass)
                val signRepository = application.container.signRepository
                val googleAuthProvider = application.container.googleAuthProvider
                val kakaoAuthProvider = application.container.kakaoAuthProvider
                val naverAuthProvider = application.container.naverAuthProvider

                SignViewModel(signRepository, googleAuthProvider, kakaoAuthProvider, naverAuthProvider)
            }
        }
    }
}
