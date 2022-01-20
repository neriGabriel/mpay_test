package com.example.mpaywalletapp.domain.repository

import com.example.mpaywalletapp.domain.entity.Statement
import com.example.mpaywalletapp.domain.utils.Resource

interface StatementRepository {
    suspend fun getStatement(id: String?) : Resource<Statement>
}