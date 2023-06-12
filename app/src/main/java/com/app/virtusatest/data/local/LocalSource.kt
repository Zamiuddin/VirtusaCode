package com.app.virtusatest.data.local

import com.app.virtusatest.data.Resource
import com.app.virtusatest.domain.model.DogBreed
import com.app.virtusatest.domain.model.DogBreedImages
import kotlinx.coroutines.flow.Flow


interface LocalSource {
    fun getDogBreeds(): Flow<List<DogBreed>>
    suspend fun getDogBreedImages(breedName: String): Resource<List<String>>
    suspend fun storeDogBreedListInDb(dogBreeds: List<DogBreed>)
    suspend fun storeDogBreedImageListInDb(breedImages: DogBreedImages)
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)
    fun getFavouriteDogBreeds(): Flow<List<DogBreed>>
}
