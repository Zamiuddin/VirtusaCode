package com.app.virtusatest.repository


import com.app.virtusatest.networkService.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MainRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getBreadList() = flow {
        val response = apiServiceImpl.getBreadList()
        emit(response)
    }.flowOn(Dispatchers.IO)


    fun getBreadSubList(name: String) = flow {
        val response = apiServiceImpl.getBreadSubList(name)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getBreadSubListImages(name: String) = flow {
        val response = apiServiceImpl.getBreadSubListImages(name)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getRandomImages(name: String) = flow {
        val response = apiServiceImpl.getRandomImages(name)
        emit(response)
    }.flowOn(Dispatchers.IO)

}