package com.example.mpaywalletapp.data.repository

import com.example.mpaywalletapp.data.api.StatementService
import com.example.mpaywalletapp.domain.entity.Statement
import com.example.mpaywalletapp.domain.repository.StatementRepository
import com.example.mpaywalletapp.domain.utils.Resource

class StatementRepositoryImpl(private val service: StatementService) : StatementRepository {
    override suspend fun getStatement(id: String?): Resource<Statement> {
        val response = service.getStatement(id)
        return if(response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message())
        }
    }

}