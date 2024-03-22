package com.example.sbtask.ui.transactions

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sbtask.data.Transaction
import com.example.sbtask.data.network.NetworkResult
import com.example.sbtask.data.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(private val remoteRepository: RemoteRepository) :
    ViewModel() {

    private val _transactionUiState = MutableStateFlow(TransactionUiState())
    val transactionUiState: StateFlow<TransactionUiState> = _transactionUiState

    init {
        getAllTransactions()
    }

    fun getAllTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.fetchAllTransactions().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        _transactionUiState.value = TransactionUiState(it.data?.transactions!!)
                    }

                    is NetworkResult.Loading -> {
                        Log.d("CardsViewModel", "loading()")
                    }

                    is NetworkResult.Error -> {
                        Log.e("CardsViewModel", "error: ${it.message}")
                    }
                }
            }
        }
    }
}

data class TransactionUiState(val transactionList: List<Transaction> = listOf())