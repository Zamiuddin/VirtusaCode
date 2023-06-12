package com.app.virtusatest.data.remote

import com.app.virtusatest.data.Resource
import com.app.virtusatest.domain.model.DogBreed

interface RemoteSource {
    suspend fun getDogBreeds(): Resource<List<DogBreed>>
    suspend fun getDogBreedImages(breedName: String): Resource<List<String>>
}

