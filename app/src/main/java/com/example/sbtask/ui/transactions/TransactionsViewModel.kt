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

    private val _transactionUiState = MutableStateFlow(TransactionUiState(loading = false))
    val transactionUiState: StateFlow<TransactionUiState> = _transactionUiState

    init {
        getAllTransactions()
    }

    private fun getAllTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            _transactionUiState.value = transactionUiState.value.copy(loading = true)
            remoteRepository.fetchAllTransactions().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        _transactionUiState.value = TransactionUiState(it.data?.transactions!!, false)
                    }

                    is NetworkResult.Loading -> {
                        Log.d("CardsViewModel", "loading()")
                        _transactionUiState.value = transactionUiState.value.copy(loading = true)
                    }

                    is NetworkResult.Error -> {
                        Log.e("CardsViewModel", "error: ${it.message}")
                        _transactionUiState.value = _transactionUiState.value.copy(loading = false)
                    }
                }
            }
        }
    }
}

data class TransactionUiState(val transactionList: List<Transaction> = listOf(), val loading: Boolean)