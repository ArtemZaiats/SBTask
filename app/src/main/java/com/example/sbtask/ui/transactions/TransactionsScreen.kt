package com.example.sbtask.ui.transactions

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sbtask.R
import com.example.sbtask.ui.components.MainTopBar
import com.example.sbtask.ui.navigation.NavigationDestination

object TransactionsDestination : NavigationDestination {
    override val route: String = "Transactions"
    override val icon: Int = R.drawable.ic_transactions_nav_bar
}

@Composable
fun TransactionsScreen(viewModel: TransactionsViewModel = hiltViewModel()) {

    Scaffold(
        topBar = { MainTopBar(title = "Transactions") }
    ) {
        TransactionsBody(modifier = Modifier.padding(it))
    }
}

@Composable
fun TransactionsBody(
    modifier: Modifier = Modifier
) {

}