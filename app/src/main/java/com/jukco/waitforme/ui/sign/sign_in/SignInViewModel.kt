package com.jukco.waitforme.ui.sign.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignInViewModel() : ViewModel() {
    var id by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set


    fun inputId(input: String) {
        id = input
    }

    fun inputPassword(input: String) {
        password = input
    }
}