package com.jukco.waitforme.ui.util

import com.jukco.waitforme.R

object ValidationChecker {

    fun checkIdValidation(id: String): Pair<Boolean, Int?> {
        val checked = id.trim()

        return when {
            checked.isBlank() -> {
                Pair(false, R.string.error_phone_number_empty)
            }
            checked.length !in 10..11 -> {
                Pair(false, R.string.error_phone_number_length)
            }
            !checked.matches(Regex("^01[016789]\\d{3,4}\\d{4}")) -> {
                Pair(false, R.string.error_phone_number_format)
            }
            else -> {
                Pair(true, null)
            }
        }
    }

    fun checkPasswordValidation(password: String): Pair<Boolean, Int?> {
        val checked = password.trim()

        return when {
            checked.isBlank() -> {
                Pair(false, R.string.error_password_empty)
            }
            checked.length !in 8..25 -> {
                Pair(false, R.string.error_password_length)
            }
            !checked.matches(
                Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@!%*#?&])[A-Za-z\\d\$@!%*#?&]{8,25}\$")
            ) -> {
                Pair(false, R.string.error_password_format)
            }
            else -> {
                Pair(true, null)
            }
        }
    }

    fun checkNameValidation(name: String): Pair<Boolean, Int?> {
        val checked = name.trim()

        return when {
            checked.isBlank() -> {
                Pair(false, R.string.error_nickname_empty)
            }
            checked.length !in 1..8 -> {
                Pair(false, R.string.error_nickname_length)
            }
            !checked.matches(
                Regex("^[가-힣|a-z|A-Z|0-9]{1,8}\$")
            ) -> {
                Pair(false, R.string.error_nickname_format)
            }
            else -> {
                Pair(true, null)
            }
        }
    }
}
