package com.example.sbtask.ui.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sbtask.ui.account.AccountDestination
import com.example.sbtask.ui.cards.CardsDestination
import com.example.sbtask.ui.home.HomeDestination
import com.example.sbtask.ui.transactions.TransactionsDestination

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val screens = listOf(
        HomeDestination,
        TransactionsDestination,
        CardsDestination,
        AccountDestination
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.border(width = 1.dp, color = Color(0xffB3B6BE), shape = RectangleShape)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach {
            NavigationBarItem(
                selected = currentRoute == it.route,
                onClick = { navController.navigate(it.route) },
                label = {
                    Text(
                        text = it.route,
                        style = TextStyle(
                            color = if (currentRoute == it.route) {
                                Color(0xff1460F3)
                            } else {
                                Color(0xffB3B6BE)
                            }
                        )
                    )
                },
                icon = {
                    Image(
                        painterResource(id = it.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        colorFilter = if (currentRoute == it.route) {
                            ColorFilter.tint(Color(0xff1460F3))
                        } else {
                            ColorFilter.tint(Color(0xffB3B6BE))
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
        }
    }
}