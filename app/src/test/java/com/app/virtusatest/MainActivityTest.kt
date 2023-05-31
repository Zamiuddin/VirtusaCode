package com.app.virtusatest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.virtusatest.viewmodel.DashboardViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: DashboardViewModel

    private lateinit var activity: MainActivity

    @Before
    fun setup() {
        activity = MainActivity()
    }

    @Test
    fun `test something in MainActivity`() {
        // Mock any necessary dependencies or setup for the ViewModel
        // For example:
        val expectedResult = "Test Result"
        `when`(viewModel.breadList).thenReturn(expectedResult)

        // Call the method or perform the action in the Activity that you want to test
        // For example:
        activity.showMessage("Success")

        // Verify the expected behavior or outcome
        // For example:
        // Assert that a specific method or property was called on the ViewModel

        // Assert the expected result or state in the Activity
        assertEquals(expectedResult, activity.showMessage("Success"))
    }
}