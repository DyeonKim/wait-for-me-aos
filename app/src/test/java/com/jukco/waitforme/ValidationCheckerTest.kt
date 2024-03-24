package com.jukco.waitforme

import com.jukco.waitforme.ui.util.ValidationChecker
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationCheckerTest {
    private val specialSymbol = "&?#*%!@$"

    @Test
    fun validationChecker_checkIdValidation_success() {
        val id1 = "01012345678"
        val id2 = "0101234567"

        assertTrue(ValidationChecker.checkIdValidation(id1).first)
        assertTrue(ValidationChecker.checkIdValidation(id2).first)
    }

    @Test
    fun validationChecker_checkIdValidation_blank() {
        val id = " "
        val result = ValidationChecker.checkIdValidation(id)

        assertFalse(result.first)
        assertEquals(R.string.error_phone_number_empty, result.second)
    }

    @Test
    fun validationChecker_checkIdValidation_lengthShorterThan() {
        val id = "010123456"
        val result = ValidationChecker.checkIdValidation(id)

        assertFalse(result.first)
        assertEquals(R.string.error_phone_number_length, result.second)
    }

    @Test
    fun validationChecker_checkIdValidation_lengthLongerThan() {
        val id = "010123456789"
        val result = ValidationChecker.checkIdValidation(id)

        assertFalse(result.first)
        assertEquals(R.string.error_phone_number_length, result.second)
    }

    @Test
    fun validationChecker_checkIdValidation_notMatchesFormat() {
        val id = "012-12*345"
        val result = ValidationChecker.checkIdValidation(id)

        assertFalse(result.first)
        assertEquals(R.string.error_phone_number_format, result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_success() {
        val password = "abcD*123"

        assertTrue(ValidationChecker.checkPasswordValidation(password).first)
    }

    @Test
    fun validationChecker_checkPasswordValidation_blank() {
        val password = " "
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals(R.string.error_password_empty, result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_lengthShorterThan() {
        val password = "abcD*12"
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals(R.string.error_password_length, result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_lengthLongerThan() {
        val password = "abcD*123" + "ab".repeat(10)
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals(R.string.error_password_length, result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_notMatchesFormat_number() {
        val password = "abcD$specialSymbol"
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals(R.string.error_password_format, result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_notMatchesFormat_alphabet() {
        val password = "1234$specialSymbol"
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals(R.string.error_password_format, result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_notMatchesFormat_specialSymbol() {
        val password = "abcD1234"
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals(R.string.error_password_format, result.second)
    }

    @Test
    fun validationChecker_checkNameValidation_success() {
        val name = "테스트"

        assertTrue(ValidationChecker.checkNameValidation(name).first)
    }

    @Test
    fun validationChecker_checkNameValidation_blank() {
        val name = " "
        val result = ValidationChecker.checkNameValidation(name)

        assertFalse(result.first)
        assertEquals(R.string.error_nickname_empty, result.second)
    }

    @Test
    fun validationChecker_checkNameValidation_lengthLongerThan() {
        val name = "나는야테스트라네하하하하"
        val result = ValidationChecker.checkNameValidation(name)

        assertFalse(result.first)
        assertEquals(R.string.error_nickname_length, result.second)
    }

    @Test
    fun validationChecker_checkNameValidation_notMatchesFormat_specialSymbol() {
        val name = "테스트*A0"
        val result = ValidationChecker.checkNameValidation(name)

        assertFalse(result.first)
        assertEquals(R.string.error_nickname_format, result.second)
    }

    @Test
    fun validationChecker_checkNameValidation_notMatchesFormat_spaceBetweenWords() {
        val name = "나는 테스트"
        val result = ValidationChecker.checkNameValidation(name)

        assertFalse(result.first)
        assertEquals(R.string.error_nickname_format, result.second)
    }

    @Test
    fun validationChecker_checkNameValidation_notMatchesFormat_unReadableWord() {
        val name = "ㅁㅏㅠ갸0A"
        val result = ValidationChecker.checkNameValidation(name)

        assertFalse(result.first)
        assertEquals(R.string.error_nickname_format, result.second)
    }
}