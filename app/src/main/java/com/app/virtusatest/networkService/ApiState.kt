package  com.app.virtusatest.networkService

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data : Any) : ApiState()
    object Empty : ApiState()
}