package com.example.mpaywalletapp.data.api

import androidx.lifecycle.LiveData
import com.example.mpaywalletapp.domain.entity.Card
import com.example.mpaywalletapp.domain.entity.Widgets
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CardService {
    @GET("card/{cardId}")
    suspend fun getCard(@Path("cardId") cardId: String?) : Response<Card>

}