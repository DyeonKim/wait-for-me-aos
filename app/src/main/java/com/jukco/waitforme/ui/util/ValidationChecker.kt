package com.jukco.waitforme.ui.util

object ValidationChecker {

    fun checkIdValidation(id: String): Pair<Boolean, String> {
        val checked = id.trim()

        return when {
            checked.isBlank() -> {
                Pair(false, "id가 비었습니다.")
            }
            checked.length !in 10..11 -> {
                Pair(false, "id의 길이가 맞지 않습니다: ${checked.length}")
            }
            !checked.matches(Regex("^01[016789]\\d{3,4}\\d{4}")) -> {
                Pair(false, "id가 올바른 전화번호 형식이 아닙니다.")
            }
            else -> {
                Pair(true, "")
            }
        }
    }

    fun checkPasswordValidation(password: String): Pair<Boolean, String> {
        val checked = password.trim()

        return when {
            checked.isBlank() -> {
                Pair(false, "비밀번호가 비었습니다.")
            }
            checked.length !in 8..25 -> {
                Pair(false, "비밀번호의 길이가 맞지 않습니다: ${checked.length}")
            }
            !checked.matches(
                Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@!%*#?&])[A-Za-z\\d\$@!%*#?&]{8,25}\$")
            ) -> {
                Pair(false, "비밀번호의 형식이 올바르지 않습니다.")
            }
            else -> {
                Pair(true, "")
            }
        }
    }
}
