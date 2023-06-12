package com.app.virtusatest.ui.component.favouriteDogBreeds

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.app.virtusatest.domain.model.DogBreed
import com.app.virtusatest.domain.usecase.favouriteDogBreeds.FavouriteDogBreedsUseCase
import com.app.virtusatest.ui.base.BaseViewModel
import com.app.virtusatest.utils.extensions.capitalizeFirstLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteDogBreedsViewModel @Inject constructor(
    private val dogBreedsUseCase: FavouriteDogBreedsUseCase
) : BaseViewModel() {
    var uiState by mutableStateOf(FavouriteUiState())
        private set

    init {
        // CoroutineScope tied to this ViewModel.
        // This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            dogBreedsUseCase.getFavouriteDogBreeds().collect { result ->
                uiState =
                    uiState.copy(
                        isLoading = false,
                        dogBreeds = result.map {
                            DogBreed(
                                name = it.name.capitalizeFirstLetter(),
                                subBreeds = it.subBreeds,
                                imageUrl = it.imageUrl,
                                isFavourite = it.isFavourite
                            )
                        })
            }
        }
    }
}