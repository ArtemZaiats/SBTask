package com.example.sbtask.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.example.sbtask.R
import com.example.sbtask.data.Transaction

@Composable
fun TransactionList(transactionList: List<Transaction>, isLoading: Boolean) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        if (isLoading) {
            items(3) {
                TransactionShimmerItem()
            }
        } else {
            items(transactionList) {
                TransactionItem(transaction = it)
            }
        }
    }
}