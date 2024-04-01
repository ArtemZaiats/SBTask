package com.example.sbtask.ui.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sbtask.R
import com.example.sbtask.data.Transaction
import com.example.sbtask.ui.components.MainTopBar
import com.example.sbtask.ui.components.TransactionList
import com.example.sbtask.ui.navigation.NavigationDestination

object TransactionsDestination : NavigationDestination {
    override val route: String = "Transactions"
    override val icon: Int = R.drawable.ic_transactions_nav_bar
}

@Composable
fun TransactionsScreen(viewModel: TransactionsViewModel = hiltViewModel()) {

    val transactionsUiState by viewModel.transactionUiState.collectAsState()
    Scaffold(
        topBar = { MainTopBar(title = "Transactions") }
    ) {
        TransactionsBody(
            modifier = Modifier.padding(it),
            transactions = transactionsUiState.transactionList,
            loading = transactionsUiState.loading
        )
    }
}

@Composable
fun TransactionsBody(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
    loading: Boolean
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        TransactionList(transactionList = transactions, isLoading = loading)
    }
}