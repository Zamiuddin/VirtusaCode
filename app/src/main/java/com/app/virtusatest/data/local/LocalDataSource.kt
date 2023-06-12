package com.app.virtusatest.data.local

import com.app.virtusatest.data.Resource
import com.app.virtusatest.data.local.persistence.DogBreedsDao
import com.app.virtusatest.domain.model.DogBreed
import com.app.virtusatest.domain.model.DogBreedImages
import com.app.virtusatest.utils.Converters
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class LocalDataSource @Inject
constructor(private val dao: DogBreedsDao) : LocalSource {

    // Function for getting Dog Breeds List from Database
    override fun getDogBreeds(): Flow<List<DogBreed>> {
        return dao.getDogBreeds()
    }

    // Function for storing Dog Breeds List to Database
    override suspend fun storeDogBreedListInDb(dogBreeds: List<DogBreed>) {
        dao.insertAllDogBreeds(dogBreeds)
    }

    // Function to get dog breed images from the database
    override suspend fun getDogBreedImages(breedName: String): Resource<List<String>> {
        val dogBreedImageList = dao.getDogBreedImages(breedName)
        return if (dogBreedImageList.isEmpty()) {
            Resource.DataError(errorCode = 2)
        } else {
            val converter = Converters()
            Resource.Success(converter.fromString(dogBreedImageList[0]))
        }
    }

    // Function for storing Dog Breed Images List to Database
    override suspend fun storeDogBreedImageListInDb(breedImages: DogBreedImages) {
        dao.insertDogBreedImages(breedImages)
    }

    // Function to update dog breeds table for favourite value in the Database
    override suspend fun updateDogBreeds(name: String, isFavourite: Boolean) {
        dao.updateDogBreeds(name, isFavourite)
    }

    // Function to get favourite dog breeds list from the Database
    override fun getFavouriteDogBreeds(): Flow<List<DogBreed>> {
        return dao.getFavouriteDogBreeds()
    }
}
