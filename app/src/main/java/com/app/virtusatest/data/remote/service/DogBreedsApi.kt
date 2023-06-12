package com.app.virtusatest.data.remote.service

import com.app.virtusatest.data.remote.dto.DogBreedImagesResponse
import com.app.virtusatest.data.remote.dto.DogBreedResponse
import com.app.virtusatest.data.remote.dto.DogBreedSingleImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedsApi {
    @GET("breeds/list/all")
    suspend fun fetchDogBreeds(): Response<DogBreedResponse>

    @GET("breed/{breed_name}/images/random/4")
    suspend fun fetchDogBreedImages(@Path("breed_name") breedName: String): Response<DogBreedImagesResponse>

    @GET("breed/{breed_name}/images/random")
    suspend fun fetchDogBreedSingleImage(@Path("breed_name") breedName: String): Response<DogBreedSingleImageResponse>
}
