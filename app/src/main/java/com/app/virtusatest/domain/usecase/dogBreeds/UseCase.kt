package com.app.virtusatest.domain.usecase.dogBreeds

import com.app.virtusatest.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getDogBreeds(): Flow<List<DogBreed>>
}