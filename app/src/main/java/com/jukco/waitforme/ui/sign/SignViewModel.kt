package com.jukco.waitforme.ui.sign

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignViewModel : ViewModel() {
    var id by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set


    fun updateID(input: String) {
        id = input
    }

    fun updatePW(input: String) {
        password = input
    }
}