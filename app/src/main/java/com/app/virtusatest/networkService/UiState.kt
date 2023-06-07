package  com.app.virtusatest.networkService

sealed class UiState{
    object Loading : UiState()
    class Failure(val msg:Throwable) : UiState()
    class Success(val data : Any) : UiState()
    object Empty : UiState()
}