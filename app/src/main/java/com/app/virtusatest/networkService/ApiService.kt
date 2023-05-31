package  com.app.virtusatest.networkService


import com.app.virtusatest.models.BreadsList
import com.app.virtusatest.models.RandomImage
import retrofit2.http.*

interface ApiService {

    @GET("breeds/list")
    suspend fun getBreadList(): BreadsList

    @GET("breed/{masterName}/list")
    suspend fun getBreadSubList(@Path("masterName") masterName: String?): BreadsList

    @GET("breed/{masterName}/images")
    suspend fun getBreadSubListImages(@Path("masterName") masterName: String?): BreadsList

    @GET("breed/{masterName}/images/random")
    suspend fun getRandomImages(@Path("masterName") masterName: String?): RandomImage

}
