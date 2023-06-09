package com.app.virtusatest.domain.usecase.favouriteDogBreeds

import com.app.virtusatest.data.repository.DataSource
import com.app.virtusatest.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavouriteDogBreedsUseCase @Inject constructor(
    private val dataRepository: DataSource
) : UseCase {
    override fun getFavouriteDogBreeds(): Flow<List<DogBreed>> {
        return dataRepository.getFavouriteDogBreeds()
    }

    override suspend fun addToFavourites(name: String, isFavourite: Boolean) {
        dataRepository.updateDogBreeds(name = name, isFavourite = isFavourite)
    }
}