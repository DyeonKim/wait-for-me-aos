package com.jukco.waitforme.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jukco.waitforme.data.network.api.UserApi
import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.data.network.model.UserInfoRequest
import com.jukco.waitforme.data.repository.UserRepository
import com.jukco.waitforme.data.repository.UserRepositoryImplementation
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

class UserRepositoryTest {
    private lateinit var server: MockWebServer
    private lateinit var userApi: UserApi
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        userApi = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(server.url("/"))
            .build()
            .create(UserApi::class.java)
        userRepository = UserRepositoryImplementation(userApi)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun userRepository_getUserInfo_success() = runTest {
        val json = Json.encodeToJsonElement(FakeDataSource.userInfoRes).toString()
        val res = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(json)
        server.enqueue(res)

        val actualRes = userRepository.getUserInfo()
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(FakeDataSource.userInfoRes, actualRes.body())
    }

    @Test
    fun userRepository_editUserInfo_success() = runTest {
        val json = Json.encodeToJsonElement(FakeDataSource.revisedUserInfoRes).toString()
        val res = MockResponse().setBody(json)
        server.enqueue(res)

        val request = UserInfoRequest("테스트라구요!", "1995-01-18", GenderType.FEMALE, null, null)
        val actualRes = userRepository.editUserInfo(request)
        server.takeRequest()

        assertTrue(actualRes.isSuccessful)
        assertEquals(FakeDataSource.revisedUserInfoRes.name, request.name)
        assertEquals(FakeDataSource.revisedUserInfoRes.birthedAt, request.birthedAt)
        assertEquals(FakeDataSource.revisedUserInfoRes.genderType, request.genderType)


    }
}