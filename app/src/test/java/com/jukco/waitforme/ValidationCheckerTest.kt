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
        assertEquals("id가 비었습니다.", result.second)
    }

    @Test
    fun validationChecker_checkIdValidation_lengthShorterThan() {
        val id = "010123456"
        val result = ValidationChecker.checkIdValidation(id)

        assertFalse(result.first)
        assertEquals("id의 길이가 맞지 않습니다: ${id.length}", result.second)
    }

    @Test
    fun validationChecker_checkIdValidation_lengthLongerThan() {
        val id = "010123456789"
        val result = ValidationChecker.checkIdValidation(id)

        assertFalse(result.first)
        assertEquals("id의 길이가 맞지 않습니다: ${id.length}", result.second)
    }

    @Test
    fun validationChecker_checkIdValidation_notMatchesFormat() {
        val id = "012-12*345"
        val result = ValidationChecker.checkIdValidation(id)

        assertFalse(result.first)
        assertEquals("id가 올바른 전화번호 형식이 아닙니다.", result.second)
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
        assertEquals("비밀번호가 비었습니다.", result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_lengthShorterThan() {
        val password = "abcD*12"
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals("비밀번호의 길이가 맞지 않습니다: ${password.length}", result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_lengthLongerThan() {
        val password = "abcD*123" + "ab".repeat(10)
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals("비밀번호의 길이가 맞지 않습니다: ${password.length}", result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_notMatchesFormat_number() {
        val password = "abcD$specialSymbol"
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals("", result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_notMatchesFormat_alphabet() {
        val password = "1234$specialSymbol"
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals("", result.second)
    }

    @Test
    fun validationChecker_checkPasswordValidation_notMatchesFormat_specialSymbol() {
        val password = "abcD1234"
        val result = ValidationChecker.checkPasswordValidation(password)

        assertFalse(result.first)
        assertEquals("", result.second)
    }
}