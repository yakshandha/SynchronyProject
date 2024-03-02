package com.example.cocktailapp.Service

import com.example.synchronyproject.BOs.Record
import retrofit2.Response
import javax.inject.Inject

interface ApiHelper {
    suspend fun getData() : Response<Record>
}

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override suspend fun getData(): Response<Record>  = apiService.getData()

}