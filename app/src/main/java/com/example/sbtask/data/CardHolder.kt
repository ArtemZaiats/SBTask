package com.example.sbtask.data

import com.google.gson.annotations.SerializedName

data class CardHolder(
    @SerializedName("id") val id: String,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("email") val email: String,
    @SerializedName("logoUrl") val logoUrl: String
)