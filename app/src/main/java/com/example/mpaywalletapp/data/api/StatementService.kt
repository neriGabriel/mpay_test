package com.example.mpaywalletapp.data.api

import com.example.mpaywalletapp.domain.entity.Statement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementService {
    @GET("statement/{accountId}")
    suspend fun getStatement(@Path("accountId") accountId: String?) : Response<Statement>
}