package com.app.virtusatest.ui.component.dogBreedDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.app.virtusatest.data.Resource
import com.app.virtusatest.domain.usecase.dogBreedImages.DogBreedImagesUseCase
import com.app.virtusatest.domain.usecase.favouriteDogBreeds.FavouriteDogBreedsUseCase
import com.app.virtusatest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DogBreedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dogBreedImagesUseCase: DogBreedImagesUseCase,
    private val favouriteDogBreedsUseCase: FavouriteDogBreedsUseCase
) : BaseViewModel() {

    private val nameType = checkNotNull(savedStateHandle["name"]).toString()

    var uiState by mutableStateOf(DogBreedDetailsUiState())
        private set

    init {
        getDogBreedImages(nameType.lowercase())
    }

    fun getDogBreedImages(name: String) {
        // CoroutineScope tied to this ViewModel.
        // This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
        viewModelScope.launch {
            dogBreedImagesUseCase.getDogBreedImages(name).collect { result ->
                handleDogBreedImagesResponse(result)
            }
        }
    }

    private fun handleDogBreedImagesResponse(result: Resource<List<String>>) {
        uiState = when (result) {
            is Resource.Success -> {
                uiState.copy(
                    isLoading = false,
                    dogBreedsImages = result.data
                )
            }
            is Resource.DataError -> {
                result.errorCode
                uiState.copy(isLoading = false, dogBreedsImages = emptyList())
            }
            is Resource.Loading -> {
                uiState.copy(isLoading = true)
            }
        }
    }

    fun addToFavourites(dogName: String, isFavourite: Boolean) {
        viewModelScope.launch {
            favouriteDogBreedsUseCase.addToFavourites(name = dogName, isFavourite = isFavourite)
        }
    }
}