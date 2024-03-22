package com.example.sbtask.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Card(
    @SerializedName("id") val id: String,
    @SerializedName("cardLast4") val cardLast4: String?,
    @SerializedName("cardName") val cardName: String,
    @SerializedName("isLocked") val isLocked: Boolean,
    @SerializedName("isTerminated") val isTerminated: Boolean,
    @SerializedName("spent") val spent: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("limitType") val limitType: String,
    @SerializedName("cardHolder") val cardHolder: CardHolder,
    @SerializedName("fundingSource") val fundingSource: String,
    @SerializedName("issuedAt") val issuedAt: String
)

data class CardResponse(
    @SerializedName("cards") val cards: List<Card>
)