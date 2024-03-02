package com.example.cocktailapp.Service

import com.example.synchronyproject.BOs.Record
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("search.php?f=b")
    suspend fun getData(): Response<Record>

}