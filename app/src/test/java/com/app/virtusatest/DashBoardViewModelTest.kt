package com.app.virtusatest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.virtusatest.models.BreadsList
import com.app.virtusatest.networkService.ApiState
import com.app.virtusatest.repository.MainRepository
import com.app.virtusatest.viewmodel.DashboardViewModel
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class DashBoardViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DashboardViewModel
    private lateinit var mainRepository: MainRepository
    private lateinit var testDispatcher: TestCoroutineDispatcher
    private lateinit var testScope: TestCoroutineScope

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        mainRepository = mock(MainRepository::class.java)
        testDispatcher = TestCoroutineDispatcher()
        testScope = TestCoroutineScope(testDispatcher)
        viewModel = DashboardViewModel(mainRepository)
    }

    @Test
    fun `test getBreadList success`() = testScope.runBlockingTest {
        val breadList: Flow<BreadsList> =
            flowOf(BreadsList(arrayListOf("qwerty", "qawseed"), "sucess"))
        val expectedState = ApiState.Success(breadList)

        // Mock the API response from the repository
        whenever(mainRepository.getBreadList()).thenReturn(breadList)

        // Call the function to be tested
        viewModel.getBreadList()

        // Advance the coroutine dispatcher to execute the suspended code
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify that the LiveData has the expected success state
        assertEquals(expectedState, viewModel.breadList.value)
    }

    @Test
    fun `test getBreadList failure`() = testScope.runBlockingTest {
        val exception = RuntimeException("API error")
        val expectedState = ApiState.Failure(exception)

        // Mock the API call to throw an exception
        whenever(mainRepository.getBreadList()).thenThrow(exception)

        // Call the function to be tested
        viewModel.getBreadList()

        // Advance the coroutine dispatcher to execute the suspended code
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify that the LiveData has the expected failure state
        assertEquals(expectedState, viewModel.breadList.value)
    }
}


// Reusable JUnit4 TestRule to override the Main dispatcher
@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}