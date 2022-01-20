package com.example.mpaywalletapp.domain.entity

import com.google.gson.annotations.SerializedName

data class Statement(
    @SerializedName("balance")
    val balance: Balance?,
    @SerializedName("transactions")
    val transactions: List<Transaction>?)
