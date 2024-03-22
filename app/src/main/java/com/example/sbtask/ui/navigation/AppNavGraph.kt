package com.example.sbtask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sbtask.ui.account.AccountDestination
import com.example.sbtask.ui.account.AccountScreen
import com.example.sbtask.ui.cards.CardDetailsDestination
import com.example.sbtask.ui.cards.CardDetailsScreen
import com.example.sbtask.ui.cards.CardsDestination
import com.example.sbtask.ui.cards.CardsScreen
import com.example.sbtask.ui.home.HomeDestination
import com.example.sbtask.ui.home.HomeScreen
import com.example.sbtask.ui.transactions.TransactionsDestination
import com.example.sbtask.ui.transactions.TransactionsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route
    ) {
        composable(HomeDestination.route) {
            HomeScreen(
                navigateToCardDetails = {
                    navController.navigate("$${CardDetailsDestination.route}/${it}")
                },
                navigateToAllCardsScreen = {
                    navController.navigate(CardsDestination.route)
                },
                navigateToTransactionsScreen = {
                    navController.navigate(TransactionsDestination.route)
                }
            )
        }
        composable(TransactionsDestination.route) { TransactionsScreen() }
        composable(CardsDestination.route) { CardsScreen() }
        composable(AccountDestination.route) { AccountScreen() }
        composable(CardDetailsDestination.route) { CardDetailsScreen(navigateBack = { navController.navigateUp() }) }
    }
}