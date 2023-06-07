package  com.app.virtusatest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.virtusatest.networkService.UiState
import com.app.virtusatest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val breadListMutable: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val breadList: StateFlow<UiState> = breadListMutable

    private val breadListSubMutable: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val breadListSub: StateFlow<UiState> = breadListSubMutable


    private val breadListSubImagesMutable: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Empty)
    val breadListSubImages: StateFlow<UiState> = breadListSubImagesMutable

    private val breadLisrandomImagesMutable: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Empty)
    val breadLisrandomImages: StateFlow<UiState> = breadLisrandomImagesMutable


    fun getBreadList() = viewModelScope.launch {
        breadListMutable.value = UiState.Loading
        mainRepository.getBreadList().catch { e -> breadListMutable.value = UiState.Failure(e) }
            .collect { data -> breadListMutable.value = UiState.Success(data) }
    }

    fun getBreadSubList(name: String) = viewModelScope.launch {
        breadListSubMutable.value = UiState.Loading
        mainRepository.getBreadSubList(name)
            .catch { e -> breadListSubMutable.value = UiState.Failure(e) }
            .collect { data -> breadListSubMutable.value = UiState.Success(data) }
    }


    fun getBreadSubListImages(name: String) = viewModelScope.launch {
        breadListSubImagesMutable.value = UiState.Loading
        mainRepository.getBreadSubListImages(name)
            .catch { e -> breadListSubImagesMutable.value = UiState.Failure(e) }
            .collect { data -> breadListSubImagesMutable.value = UiState.Success(data) }
    }

    fun getRandomImages(name: String) = viewModelScope.launch {
        breadLisrandomImagesMutable.value = UiState.Loading
        mainRepository.getRandomImages(name)
            .catch { e -> breadLisrandomImagesMutable.value = UiState.Failure(e) }
            .collect { data -> breadLisrandomImagesMutable.value = UiState.Success(data) }
    }

}