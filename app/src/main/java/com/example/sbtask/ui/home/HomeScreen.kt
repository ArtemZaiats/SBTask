package com.example.sbtask.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sbtask.R
import com.example.sbtask.data.Card
import com.example.sbtask.data.Transaction
import com.example.sbtask.ui.cards.CardsViewModel
import com.example.sbtask.ui.components.CardsList
import com.example.sbtask.ui.components.MainTopBar
import com.example.sbtask.ui.components.ShimmerCardItem
import com.example.sbtask.ui.components.TransactionList
import com.example.sbtask.ui.navigation.NavigationDestination
import com.example.sbtask.ui.transactions.TransactionUiState
import com.example.sbtask.ui.transactions.TransactionsViewModel

object HomeDestination : NavigationDestination {
    override val route: String = "Home"
    override val icon: Int = R.drawable.ic_home_nav_bar
}

@Composable
fun HomeScreen(
    cardsViewModel: CardsViewModel = hiltViewModel(),
    transactionsViewModel: TransactionsViewModel = hiltViewModel(),
    navigateToCardDetails: (String) -> Unit,
    navigateToAllCardsScreen: () -> Unit,
    navigateToTransactionsScreen: () -> Unit,
) {

    val cardsUiState by cardsViewModel.cardUiState.collectAsState()
    val transactionUiState by transactionsViewModel.transactionUiState.collectAsState()
    val cardsLoading by cardsViewModel.loading.collectAsState()

    Scaffold(
        topBar = { MainTopBar(title = "Money") }
    ) { paddingValues ->
        HomeBody(
            cardList = cardsUiState.cardList,
            transactionUiState = transactionUiState,
            loading = cardsLoading,
            navigateToAllCards = navigateToAllCardsScreen,
            navigateToTransactions = navigateToTransactionsScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }

}

@Composable
fun HomeBody(
    cardList: List<Card>,
    transactionUiState: TransactionUiState,
    loading: Boolean,
    navigateToAllCards: () -> Unit,
    navigateToTransactions: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xffF6F7F9))
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        AvailableBalance()
        BalanceActionBar()
        Cards(
            cardList = cardList,
            loading = loading,
            navigateToAllCards = navigateToAllCards
        )
        RecentTransactions(
            transactionList = transactionUiState.transactionList,
            loading = transactionUiState.loading,
            navigateToTransactions = navigateToTransactions
        )
    }
}

@Composable
fun AvailableBalance() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .clickable {
                //TODO
            }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painterResource(id = R.drawable.ic_usa_flag),
                    contentDescription = "currency flag"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "USD account",
                    style = TextStyle(
                        color = Color(0xff21222E),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500)
                    )
                )
            }

            Image(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Color.Black),
                modifier = Modifier.size(24.dp)
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$100,000",
            style = TextStyle(
                color = Color(0xff21222E),
                fontSize = 28.sp,
                fontWeight = FontWeight(800)
            )
        )
    }
}

@Composable
fun BalanceActionBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clickable {
                    //TODO
                }
        ) {
            Icon(painterResource(id = R.drawable.ic_arrow_down_left), contentDescription = null)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Add funds",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                )
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(0.5.dp)
                .background(color = Color(0xffF0F1F3))
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clickable {
                    //TODO
                }
        ) {
            Icon(painterResource(id = R.drawable.ic_arrow_up_right), contentDescription = null)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Transfer",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                )
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(0.5.dp)
                .background(color = Color(0xffF0F1F3))
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clickable {
                    //TODO
                }
        ) {
            Icon(painterResource(id = R.drawable.ic_credit_card), contentDescription = null)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Add card",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                )
            )
        }
    }
}

@Composable
fun Cards(
    cardList: List<Card>,
    loading: Boolean,
    navigateToAllCards: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.my_cards),
                style = TextStyle(
                    color = Color(0xff2B2C39),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                )
            )

            TextButton(
                onClick = { navigateToAllCards() },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.see_all),
                    style = TextStyle(
                        color = Color(0xff1460F3),
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CardsList(
            cards = if (cardList.size > 3) cardList.subList(0, 3) else cardList,
            isLoading = loading
        )
    }
}

@Composable
fun RecentTransactions(
    transactionList: List<Transaction>,
    loading: Boolean,
    navigateToTransactions: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.recent_transactions),
                style = TextStyle(
                    color = Color(0xff2B2C39),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                )
            )

            TextButton(
                onClick = { navigateToTransactions() },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.see_all),
                    style = TextStyle(
                        color = Color(0xff1460F3),
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TransactionList(
            transactionList =
            if (transactionList.size > 3) {
                transactionList.subList(0, 3)
            } else transactionList,
            isLoading = loading
        )
    }
}