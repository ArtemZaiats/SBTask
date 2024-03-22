package com.example.sbtask.data

import com.google.gson.annotations.SerializedName


data class Transaction(
    @SerializedName("id") val id: String,
    @SerializedName("publicId") val publicId: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("type") val type: String,
    @SerializedName("status") val status: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("merchant") val merchant: Merchant?,
    @SerializedName("card") val card: Card?,
    @SerializedName("account") val account: Account,
    @SerializedName("category") val category: Any?,
    @SerializedName("attachments") val attachments: List<Any>,
    @SerializedName("createDate") val createDate: String,
    @SerializedName("completeDate") val completeDate: String
)

data class Merchant(
    @SerializedName("name") val name: String?,
    @SerializedName("icon") val icon: String?
)


data class Account(
    @SerializedName("accountName") val accountName: String,
    @SerializedName("accountLast4") val accountLast4: String,
    @SerializedName("accountType") val accountType: String
)

data class TransactionResponse(
    @SerializedName("transactions") val transactions: List<Transaction>
)