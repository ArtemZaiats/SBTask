package com.example.sbtask.ui.components

import android.provider.CalendarContract.Colors
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sbtask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = Color(0xff2B2C39),
                    fontSize = 32.sp,
                    fontWeight = FontWeight(800)
                )
            )
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = modifier.size(44.dp)
            ) {
                Image(painterResource(id = R.drawable.ic_plus), contentDescription = "add card")
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = modifier.size(44.dp)
            ) {
                Image(painterResource(id = R.drawable.ic_bank), contentDescription = "add card")
            }
        }
    )
}