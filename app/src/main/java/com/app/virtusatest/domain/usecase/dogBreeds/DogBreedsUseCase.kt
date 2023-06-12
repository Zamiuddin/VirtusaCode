package com.app.virtusatest.domain.usecase.dogBreeds

import com.app.virtusatest.data.repository.DataSource
import com.app.virtusatest.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogBreedsUseCase @Inject constructor(
    private val dataRepository: DataSource
) : UseCase {
     override fun getDogBreeds(): Flow<List<DogBreed>> {
        return dataRepository.getDogBreeds()
    }
}