package com.jukco.waitforme.ui.sign.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jukco.waitforme.ui.util.ValidationChecker

sealed interface SignInEvent {
    data class InputId(val id: String) : SignInEvent
    data class InputPassword(val password: String) : SignInEvent
    data class CheckSignInValid(val signIn: (String, String) -> Unit) : SignInEvent
}
class SignInViewModel : ViewModel() {
    var form by mutableStateOf(SignInForm())
        private set

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.InputId -> { inputId(event.id) }
            is SignInEvent.InputPassword -> { inputPassword(event.password) }
            is SignInEvent.CheckSignInValid -> { checkSignInValid(event.signIn) }
        }
    }

    private fun inputId(input: String) {
        form = form.copy(id = input)
    }

    private fun inputPassword(input: String) {
        form = form.copy(password = input)
    }

    private fun checkSignInValid(signIn: (id: String, password: String) -> Unit) {
        if (checkId() and checkPassword()) {
            form = form.copy(hasError = false)
            signIn(form.id, form.password)
        } else {
            form = form.copy(hasError = true)
        }
    }

    private fun checkId(): Boolean = ValidationChecker.checkIdValidation(form.id).first

    private fun checkPassword() = ValidationChecker.checkPasswordValidation(form.password).first
}
