package com.app.virtusatest.usecase


import com.app.virtusatest.data.repository.DataRepository
import com.app.virtusatest.domain.model.DogBreed
import com.app.virtusatest.domain.usecase.dogBreeds.DogBreedsUseCase
import com.app.virtusatest.utils.CoroutineTestRule
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class DogBreedsUseCaseTest {
    lateinit var dogBreedsUseCase: DogBreedsUseCase

    private val dataRepository = mockk<DataRepository>()


    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        dogBreedsUseCase = DogBreedsUseCase(dataRepository)
    }

    @Test
    fun `fetch dog breeds`() = runTest {
        // given
        val mockDogBreeds = mockk<List<DogBreed>>(relaxed = true)

        every {
            dataRepository.getDogBreeds()
        } returns flow { emit(mockDogBreeds) }

        // when
        val useCaseValue =
            dogBreedsUseCase.getDogBreeds()

        //then
        useCaseValue.first() shouldBe mockDogBreeds
    }
}