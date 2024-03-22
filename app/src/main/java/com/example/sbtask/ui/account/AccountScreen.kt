package com.example.sbtask.ui.account

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sbtask.R
import com.example.sbtask.ui.components.MainTopBar
import com.example.sbtask.ui.navigation.NavigationDestination

object AccountDestination : NavigationDestination {
    override val route: String = "Account"
    override val icon: Int = R.drawable.ic_account_nav_bar
}

@Composable
fun AccountScreen() {
    Scaffold(
        topBar = { MainTopBar(title = "Account")}
    ) {
        paddingValues ->
        AccountBody(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun AccountBody(modifier: Modifier) {

}