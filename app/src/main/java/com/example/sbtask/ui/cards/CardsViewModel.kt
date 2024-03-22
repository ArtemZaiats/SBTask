package com.example.sbtask.ui.cards

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sbtask.data.Card
import com.example.sbtask.data.network.NetworkResult
import com.example.sbtask.data.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(private val remoteRepository: RemoteRepository) :
    ViewModel() {

    private val _cardUiState = MutableStateFlow(CardUiState())
    val cardUiState: StateFlow<CardUiState> = _cardUiState

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        getAllCards()
    }

    fun getAllCards() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            remoteRepository.fetchAllCards()
                .collect {
                    when(it) {
                        is NetworkResult.Success -> {
                            _cardUiState.value = CardUiState(it.data?.cards!!)
                            _loading.value = false
                        }
                        is NetworkResult.Loading -> {
                            Log.d("CardsViewModel", "loading()")
                            _loading.value = true
                        }
                        is NetworkResult.Error -> {
                            Log.e("CardsViewModel", "error: ${it.message}")
                            _loading.value = false
                        }
                    }

                }
        }
    }

}

data class CardUiState(val cardList: List<Card> = listOf())