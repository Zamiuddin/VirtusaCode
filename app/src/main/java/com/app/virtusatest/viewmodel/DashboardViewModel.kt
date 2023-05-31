package  com.app.virtusatest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.virtusatest.networkService.ApiState
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

    private val breadListMutable: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val breadList: StateFlow<ApiState> = breadListMutable

    private val breadListSubMutable: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val breadListSub: StateFlow<ApiState> = breadListSubMutable


    private val breadListSubImagesMutable: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)
    val breadListSubImages: StateFlow<ApiState> = breadListSubImagesMutable

    private val breadLisrandomImagesMutable: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)
    val breadLisrandomImages: StateFlow<ApiState> = breadLisrandomImagesMutable


    fun getBreadList() = viewModelScope.launch {
        breadListMutable.value = ApiState.Loading
        mainRepository.getBreadList().catch { e -> breadListMutable.value = ApiState.Failure(e) }
            .collect { data -> breadListMutable.value = ApiState.Success(data) }
    }

    fun getBreadSubList(name: String) = viewModelScope.launch {
        breadListSubMutable.value = ApiState.Loading
        mainRepository.getBreadSubList(name)
            .catch { e -> breadListSubMutable.value = ApiState.Failure(e) }
            .collect { data -> breadListSubMutable.value = ApiState.Success(data) }
    }


    fun getBreadSubListImages(name: String) = viewModelScope.launch {
        breadListSubImagesMutable.value = ApiState.Loading
        mainRepository.getBreadSubListImages(name)
            .catch { e -> breadListSubImagesMutable.value = ApiState.Failure(e) }
            .collect { data -> breadListSubImagesMutable.value = ApiState.Success(data) }
    }

    fun getRandomImages(name: String) = viewModelScope.launch {
        breadLisrandomImagesMutable.value = ApiState.Loading
        mainRepository.getRandomImages(name)
            .catch { e -> breadLisrandomImagesMutable.value = ApiState.Failure(e) }
            .collect { data -> breadLisrandomImagesMutable.value = ApiState.Success(data) }
    }

}