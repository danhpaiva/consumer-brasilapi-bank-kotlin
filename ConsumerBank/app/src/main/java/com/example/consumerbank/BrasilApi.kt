package com.example.consumerbank

import com.example.consumerbank.model.Bank
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BrasilApi {
    @GET("api/banks/v1/{id}")
    suspend fun getBank(@Path("id") id: String) : Response<Bank>
}