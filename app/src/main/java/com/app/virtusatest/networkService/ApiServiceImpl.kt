package  com.app.virtusatest.networkService

import com.app.virtusatest.models.BreadsList
import com.app.virtusatest.models.RandomImage
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private var apiService: ApiService) {

    suspend fun getBreadList(): BreadsList = apiService.getBreadList()

    suspend fun getBreadSubList(name: String): BreadsList = apiService.getBreadSubList(name)

    suspend fun getBreadSubListImages(name: String): BreadsList = apiService.getBreadSubListImages(name)

    suspend fun getRandomImages(name: String): RandomImage = apiService.getRandomImages(name)
}
