package com.app.virtusatest.data.remote.dto


/** A data class is a class that only contains state and does not perform any operation. **/
data class DogBreedResponse(
    val status: String,
    val message: Map<String, List<String>>,
    val code: Int,
) {
    companion object {
        const val SUCCESS_STATUS = "success"
    }
}
