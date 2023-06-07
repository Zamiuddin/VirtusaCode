package com.app.virtusatest

import com.app.virtusatest.di.RetrofitClient
import com.app.virtusatest.models.BreadsList
import com.app.virtusatest.networkService.ApiService
import com.app.virtusatest.networkService.AppAPi
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class RetrofitClientTest {

    private lateinit var apiService: ApiService
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setup() {
        // Create a mock ApiService
        apiService = mock(ApiService::class.java)

        // Create a Retrofit instance with the mock ApiService
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .build()
        apiService = retrofit.create()

        // Create the RetrofitClient instance with the mock ApiService
        retrofitClient = com.app.virtusatest.di.RetrofitClient(apiService)
    }

    @Test
    fun `test successful API call`() = runBlocking {
        val expectedResponse = BreadsList()
        val url = "https://dog.ceo/api/"

        // Create a mock Response object with a successful response
        // val response = Response.success(expectedResponse)

        // Mock the ApiService method to return the mock Response object
        whenever(apiService.getBreadList()).thenReturn(expectedResponse)

        // Make the API call using the RetrofitClient
        val actualResponse =
            retrofitClient.provideRetrofit(okHttpClient = OkHttpClient(), AppAPi.BASE_URL)

        // Verify that the ApiService method is called
        verify(apiService).getBreadList()

        // Verify that the actual response matches the expected response
        assertEquals(expectedResponse, actualResponse)
    }

    @Test(expected = RuntimeException::class)
    fun `test API call failure`() = runBlocking {
        val url = "https://dog.ceo/api/"

        // Create a mock Response object with a failure response
        val response = Response.error<String>(400, TestingConstants.ERROR.toResponseBody(null))

        // Mock the ApiService method to return the mock Response object
        whenever(apiService.getBreadList()).thenReturn(response)

        // Make the API call using the RetrofitClient (should throw an exception)
        retrofitClient.provideRetrofit(okHttpClient = OkHttpClient(), AppAPi.BASE_URL)

        // Verify that the ApiService method is called
        verify(apiService).getBreadList()
    }
}