package com.example.sbtask.ui.components

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.sbtask.R
import com.example.sbtask.data.Card

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItem(card: Card, showArrow: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(32.dp)
                    .background(color = Color(0xff2B2C39), shape = RoundedCornerShape(6.dp))
                    .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(6.dp))
            ) {
                Text(
                    text = card.cardLast4 ?: "",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.BottomEnd)
                )

//                GlideImage(
//                    model = card.cardHolder.logoUrl,
//                    contentDescription = "logo icon",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.size(28.dp)
//                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = card.cardName,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            )
        }
        if (showArrow) {
            Image(painterResource(id = R.drawable.ic_arrow_next), contentDescription = "next icon")
        }
    }
}