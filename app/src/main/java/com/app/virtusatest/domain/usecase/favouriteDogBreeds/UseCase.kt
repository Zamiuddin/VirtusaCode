package com.app.virtusatest.domain.usecase.favouriteDogBreeds

import com.app.virtusatest.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow


interface UseCase {
    fun getFavouriteDogBreeds(): Flow<List<DogBreed>>
    suspend fun addToFavourites(name: String, isFavourite: Boolean)
}