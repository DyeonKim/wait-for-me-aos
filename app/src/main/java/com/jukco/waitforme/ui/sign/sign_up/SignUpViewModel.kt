package com.jukco.waitforme.ui.sign.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var phoneNumber by mutableStateOf("")
        private set
    var verificationCode by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var verificationPW by mutableStateOf("")
        private set
    var phoneNumberSubmitted by mutableStateOf(false)
        private set
    var verificationSubmitted by mutableStateOf(false)
        private set


    fun inputPhoneNumber(input: String) {
        phoneNumber = input
    }

    fun inputVerificationCode(input: String) {
        verificationCode = input
    }

    fun inputPassword(input: String) {
        password = input
    }

    fun inputVerificationPW(input: String) {
        verificationPW = input
    }

    fun submitPhoneNumber() {
        // TODO: phoneNumber가 비어있지 않아야하고 폰번호 형식을 지켜야 한다.
        phoneNumberSubmitted = true
    }

    fun submitVerificationCode() {
        verificationSubmitted = true
    }

    fun submitPassword(): Boolean {
        // TODO: 비밀번호 규칙 확인, 비밀번호 == 재확인 비밀번호 체크
        return true
    }
}