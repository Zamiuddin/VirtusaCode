package com.app.virtusatest.di

import androidx.databinding.ktx.BuildConfig
import com.app.virtusatest.data.local.LocalDataSource
import com.app.virtusatest.data.local.LocalSource
import com.app.virtusatest.data.local.persistence.DogBreedsDao
import com.app.virtusatest.data.remote.RemoteDataSource
import com.app.virtusatest.data.remote.RemoteSource
import com.app.virtusatest.data.remote.service.DogBreedsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideDogBreedsService(retrofit: Retrofit): DogBreedsApi {
        return retrofit.create(DogBreedsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(api: DogBreedsApi): RemoteSource {
        return RemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: DogBreedsDao): LocalSource {
        return LocalDataSource(dao)
    }
}
