package com.app.virtusatest.ui.component.favouriteDogBreeds

import com.app.virtusatest.domain.model.DogBreed


data class FavouriteUiState (
    val dogBreeds: List<DogBreed>? = null,
    val isLoading: Boolean = false,
)