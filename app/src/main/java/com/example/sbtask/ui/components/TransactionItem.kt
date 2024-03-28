package com.example.sbtask.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.substring
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.sbtask.R
import com.example.sbtask.data.Transaction

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = Color(0xffF6F7F9), shape = CircleShape)
        ) {
            GlideImage(
                model = transaction.merchant?.icon,
                contentDescription = "logo icon",
                contentScale = ContentScale.Crop,
                failure = placeholder(R.drawable.ic_credit_card),
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = transaction.merchant?.name ?: stringResource(R.string.payment),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xff2B2C39)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (!transaction.card?.cardLast4.isNullOrEmpty()) {
                    Text(
                        text = "•• ${transaction.card?.cardLast4}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xff7E8493)
                        ),
                        modifier = if (transaction.card?.cardLast4!!.isNotEmpty()) Modifier else Modifier
                            .height(0.dp)
                            .width(0.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = formatAmount(transaction.amount),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = if (transaction.amount > 0) {
                        Color(0xff00AC4F)
                    } else {
                        Color(0xff2B2C39)
                    }
                ),
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painterResource(id = if (transaction.status == "Success") R.drawable.ic_receipt else R.drawable.ic_card_nav_bar),
                contentDescription = "receipt image"
            )
        }
    }
}

fun formatAmount(amount: Double): String {
    val formattedAmount = if (amount % 1 == 0.0) {
        amount.toInt().toString()
    } else {
        amount.toString()
    }

    return if (amount <= 0) {
        "-$${formattedAmount.substring(1)}"
    } else {
        "$$formattedAmount"
    }
}
