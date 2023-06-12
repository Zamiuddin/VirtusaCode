package com.app.virtusatest.data.remote.dto


/** A data class is a class that only contains state and does not perform any operation. **/
data class DogBreedImagesResponse(
    val status: String,
    val message: List<String>,
    val code: Int
)
