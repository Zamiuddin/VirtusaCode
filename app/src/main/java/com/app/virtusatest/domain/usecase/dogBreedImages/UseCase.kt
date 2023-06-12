package com.app.virtusatest.domain.usecase.dogBreedImages

import com.app.virtusatest.data.Resource
import kotlinx.coroutines.flow.Flow

interface UseCase {
    suspend fun getDogBreedImages(breed : String): Flow<Resource<List<String>>>
}