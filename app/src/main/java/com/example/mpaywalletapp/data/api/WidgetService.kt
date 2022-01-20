package com.example.mpaywalletapp.data.api

import com.example.mpaywalletapp.domain.entity.Widgets
import retrofit2.Response
import retrofit2.http.GET

interface WidgetService {
    @GET("home")
    suspend fun getWidgets(): Response<Widgets>
}