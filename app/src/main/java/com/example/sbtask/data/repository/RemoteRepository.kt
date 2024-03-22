package com.example.sbtask.data.repository

import com.example.sbtask.data.CardResponse
import com.example.sbtask.data.Transaction
import com.example.sbtask.data.TransactionResponse
import com.example.sbtask.data.network.BankApiService
import com.example.sbtask.data.network.BaseApiResponse
import com.example.sbtask.data.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: BankApiService) :
    BaseApiResponse() {

    suspend fun fetchAllCards(): Flow<NetworkResult<CardResponse>> = flow {
        emit(safeApiCall { apiService.getAllCards() })
    }

    suspend fun fetchAllTransactions(): Flow<NetworkResult<TransactionResponse>> = flow {
        emit(safeApiCall { apiService.getAllTransactions() })
    }
}