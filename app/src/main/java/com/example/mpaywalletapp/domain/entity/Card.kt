package com.example.mpaywalletapp.domain.entity

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("cardNumber")
    val cardNumber: String?,
    @SerializedName("cardName")
    val cardName: String?,
    @SerializedName("expirationDate")
    val expirationDate: String?,
    @SerializedName("availableLimit")
    val availableLimit: String?,
    @SerializedName("totalLimit")
    val totalLimit: String?)
