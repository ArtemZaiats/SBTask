package com.example.sbtask.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sbtask.R
import com.example.sbtask.data.Card
import com.example.sbtask.ui.components.CardsList
import com.example.sbtask.ui.components.MainTopBar
import com.example.sbtask.ui.navigation.NavigationDestination

object CardsDestination : NavigationDestination {
    override val route: String = "My Cards"
    override val icon: Int = R.drawable.ic_card_nav_bar
}

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    cardsViewModel: CardsViewModel = hiltViewModel()
) {
    val cardsUiState by cardsViewModel.cardUiState.collectAsState()
    val isLoading by cardsViewModel.loading.collectAsState()

    Scaffold(
        topBar = { MainTopBar(title = "My cards") }
    ) { paddingValues ->
        CardsBody(
            modifier = modifier.padding(paddingValues),
            cards = cardsUiState.cardList,
            isLoading = isLoading
            )
    }
}

@Composable
fun CardsBody(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    isLoading: Boolean
    ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        CardsList(cards = cards, isLoading = isLoading, showArrow = true)
    }
}