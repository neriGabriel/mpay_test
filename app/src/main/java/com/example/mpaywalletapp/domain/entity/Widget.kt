package com.example.mpaywalletapp.domain.entity

import com.google.gson.annotations.SerializedName

data class Widgets(
    @SerializedName("widgets")
    val widgetList: List<Widget>
)

data class Widget (
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("content")
    val content: WidgetContent)

data class WidgetContent(
        @SerializedName("title")
        val title: String,
        @SerializedName("cardNumber")
        val cardNumber: String? = null,
        @SerializedName("button")
        val button: WidgetButton? = null,
        @SerializedName("balance")
        val balance: Balance? = null)

data class WidgetButton(
        @SerializedName("text")
        val text: String,
        @SerializedName("action")
        val action: WidgetAction)

data class WidgetAction(
        @SerializedName("identifier")
        val identifier: String,
        @SerializedName("content")
        val content: WidgetActionContent)

data class WidgetActionContent(
        @SerializedName("accountId")
        val accountId: String,
        @SerializedName("cardId")
        val cardId: String)

data class Balance(
        @SerializedName("label")
        val label: String,
        @SerializedName("value")
        val value: String)