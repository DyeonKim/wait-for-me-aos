package com.jukco.waitforme.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jukco.waitforme.data.network.api.SignApi
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
        val res = MockResponse().setBody(json)
        server.enqueue(res)

        val phoneNumAndPassword = mapOf("phoneNumber" to "01012345678", "password" to "test123!")
        val actualRes = signRepository.localSignIn(phoneNumAndPassword)
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(FakeDataSource.signInResponse, actualRes.body())
    }

    fun signRepository_socialSignIn_success() = runTest {
        val json = Json.encodeToJsonElement(FakeDataSource.signInResponse).toString()
        val res = MockResponse().setBody(json)
        server.enqueue(res)

        val actualRes = signRepository.socialSignIn("test123%23")
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(FakeDataSource.signInResponse, actualRes.body())
    }
}