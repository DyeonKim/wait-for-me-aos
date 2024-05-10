package com.jukco.waitforme.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jukco.waitforme.data.network.api.SignApi
import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.data.network.model.LocalSignInRequest
import com.jukco.waitforme.data.network.model.LocalSignUpRequest
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.data.network.model.SocialSignInRequest
import com.jukco.waitforme.data.network.model.SocialSignUpRequest
import com.jukco.waitforme.data.repository.SignRepository
import com.jukco.waitforme.data.repository.SignRepositoryImplementation
import com.jukco.waitforme.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.net.HttpURLConnection

class SignRepositoryTest {
    private lateinit var server: MockWebServer
    private lateinit var signApi: SignApi
    private lateinit var signRepository: SignRepository

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        signApi = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(server.url("/"))
            .build()
            .create(SignApi::class.java)
        signRepository = SignRepositoryImplementation(signApi)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun signRepository_localSignIn_success() = runTest {
        val json = Json.encodeToJsonElement(FakeDataSource.signInResponse).toString()
        val res = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(json)
        server.enqueue(res)

        val request = LocalSignInRequest("01012345678", "test123!")
        val actualRes = signRepository.localSignIn(request)
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(HttpURLConnection.HTTP_OK, actualRes.code())
        assertEquals(FakeDataSource.signInResponse, actualRes.body())
    }

    @Test
    fun signRepository_socialSignIn_success() = runTest {
        val json = Json.encodeToJsonElement(FakeDataSource.signInResponse).toString()
        val res = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(json)
        server.enqueue(res)

        val request = SocialSignInRequest(Provider.NAVER, "test123%23")
        val actualRes = signRepository.socialSignIn(request)
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(HttpURLConnection.HTTP_OK, actualRes.code())
        assertEquals(FakeDataSource.signInResponse, actualRes.body())
    }

    @Test
    fun signRepository_socialSignIn_noContent() = runTest {
        val res = MockResponse().setResponseCode(HttpURLConnection.HTTP_NO_CONTENT)
        server.enqueue(res)

        val request = SocialSignInRequest(Provider.NAVER, "test123%23")
        val actualRes = signRepository.socialSignIn(request)
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(HttpURLConnection.HTTP_NO_CONTENT, actualRes.code())
    }

    @Test
    fun signRepository_localSignUp_success() = runTest {
        val json = Json.encodeToJsonElement(FakeDataSource.signInResponse).toString()
        val res = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(json)
        server.enqueue(res)

        val request = LocalSignUpRequest("01012345678", "", "test!123", false)
        val actualRes = signRepository.localSignUp(request)
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(HttpURLConnection.HTTP_OK, actualRes.code())
        assertEquals(FakeDataSource.signInResponse, actualRes.body())
    }

    @Test
    fun signRepository_socialSignUp_success() = runTest {
        val json = Json.encodeToJsonElement(FakeDataSource.signInResponse).toString()
        val res = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(json)
        server.enqueue(res)

        val request = SocialSignUpRequest(Provider.NAVER, "test", "01012345678", false, "테스트입니다.", "", GenderType.OTHER, "")
        val actualRes = signRepository.socialSignUp(request)
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(HttpURLConnection.HTTP_OK, actualRes.code())
        assertEquals(FakeDataSource.signInResponse, actualRes.body())
    }

    @Test
    fun signRepository_checkDuplicateName_success() = runTest {
        val json = Json.encodeToJsonElement(true).toString()
        val res = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(json)
        server.enqueue(res)

        val actualRes = signRepository.checkDuplicateName("test")
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(HttpURLConnection.HTTP_OK, actualRes.code())
        assertEquals(true, actualRes.body())
    }

    @Test
    fun signRepository_checkDuplicateName_fail() = runTest {
        val json = Json.encodeToJsonElement(false).toString()
        val res = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(json)
        server.enqueue(res)

        val actualRes = signRepository.checkDuplicateName("test")
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(HttpURLConnection.HTTP_OK, actualRes.code())
        assertEquals(false, actualRes.body())
    }
}