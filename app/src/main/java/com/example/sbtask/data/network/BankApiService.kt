package com.example.sbtask.data.network

import com.example.sbtask.data.CardResponse
import com.example.sbtask.data.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET

interface BankApiService {

    @GET("cards")
    suspend fun getAllCards() : Response<CardResponse>

    @GET("cards/transactions")
    suspend fun getAllTransactions(): Response<TransactionResponse>
}