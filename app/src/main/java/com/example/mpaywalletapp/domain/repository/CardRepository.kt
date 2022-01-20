package com.example.mpaywalletapp.domain.repository

import com.example.mpaywalletapp.domain.entity.Card
import com.example.mpaywalletapp.domain.utils.Resource

interface CardRepository {
    suspend fun getCard(id: String?) : Resource<Card>
}