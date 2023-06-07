package com.app.virtusatest

import com.app.virtusatest.models.BreadsList
import com.app.virtusatest.networkService.ApiServiceImpl
import com.app.virtusatest.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var apiService: ApiServiceImpl

    private lateinit var mainRepository: MainRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getBreadList emits correct response`() = runBlockingTest {
        // Arrange
        val expectedResponse: BreadsList =
            BreadsList(
                arrayListOf(TestingConstants.QWERTY, TestingConstants.KEYBOARD),
                TestingConstants.SUCCESS
            )
        `when`(apiService.getBreadList()).thenReturn(expectedResponse)

        // Act
        val actualResponse = mutableListOf<BreadsList>()
        mainRepository.getBreadList().collect { response ->
            actualResponse.addAll(actualResponse)
        }

        // Assert
        assertEquals(expectedResponse, actualResponse)
    }
}