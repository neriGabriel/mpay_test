package com.example.mpaywalletapp.data.repository

import com.example.mpaywalletapp.data.api.CardService
import com.example.mpaywalletapp.domain.entity.Card
import com.example.mpaywalletapp.domain.repository.CardRepository
import com.example.mpaywalletapp.domain.utils.Resource

class CardRepositoryImpl(private val service: CardService) : CardRepository {
    override suspend fun getCard(id: String?): Resource<Card> {
        val response = service.getCard(id)
        return if(response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message())
        }
    }

}