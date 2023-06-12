package com.app.virtusatest.data.repository

import com.app.virtusatest.data.Resource
import com.app.virtusatest.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow


interface DataSource {
    fun getDogBreeds(): Flow<List<DogBreed>>
    suspend fun getDogBreedImages(breedName: String): Flow<Resource<List<String>>>
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)
    fun getFavouriteDogBreeds(): Flow<List<DogBreed>>
}
