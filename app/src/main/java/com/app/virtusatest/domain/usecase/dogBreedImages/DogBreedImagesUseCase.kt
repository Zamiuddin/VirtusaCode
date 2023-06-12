package com.app.virtusatest.domain.usecase.dogBreedImages

import com.app.virtusatest.data.Resource
import com.app.virtusatest.data.repository.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogBreedImagesUseCase @Inject constructor(
    private val dataRepository: DataSource
) : UseCase {
    override suspend fun getDogBreedImages(breed: String): Flow<Resource<List<String>>> {
        return dataRepository.getDogBreedImages(breed)
    }
}