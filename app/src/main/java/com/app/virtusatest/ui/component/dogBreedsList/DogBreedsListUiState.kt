package com.app.virtusatest.ui.component.dogBreedsList

import com.app.virtusatest.domain.model.DogBreed

data class DogBreedsListUiState (
    var dogBreeds: List<DogBreed>? = null,
    val isLoading: Boolean = true
)