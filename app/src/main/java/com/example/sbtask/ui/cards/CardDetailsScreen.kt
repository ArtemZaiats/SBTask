package com.example.sbtask.ui.cards

import android.telecom.Call.Details
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.sbtask.R
import com.example.sbtask.data.Card
import com.example.sbtask.ui.navigation.NavigationDestination

object CardDetailsDestination : NavigationDestination {
    override val route: String = "Card Details"
    override val icon: Int = R.drawable.ic_card_nav_bar
    const val cardIdArg = "cardId"
    val routeWithArgs = "$route/{$cardIdArg}"
}

@Composable
fun CardDetailsScreen(
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = { DetailsTopBar() }
    ) { paddingValues ->
        CardDetailsBody(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun CardDetailsBody(modifier: Modifier = Modifier) {

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun DetailsTopBar() {
    TopAppBar(
        title = {
            Row {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = Color.White, shape = CircleShape)
                ) {
                    GlideImage(
                        model = "dsd",
                        contentDescription = "logo icon",
                        contentScale = ContentScale.Crop,
                        failure = placeholder(R.drawable.ic_card_nav_bar),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(color = Color.Transparent, shape = CircleShape)
                            .size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Slack",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500)
                    )
                )

                Text(
                    text = "•• 4444",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500)
                    )
                )
            }
        }, navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back_icon)
            )
        }
    )
}