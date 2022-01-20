package com.example.mpaywalletapp.presentation.feature.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpaywalletapp.data.api.CardService
import com.example.mpaywalletapp.data.repository.CardRepositoryImpl
import com.example.mpaywalletapp.data.utils.NetworkUtils
import com.example.mpaywalletapp.domain.entity.Card
import com.example.mpaywalletapp.domain.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class CardViewModel(private val cardRepository: CardRepositoryImpl) : ViewModel() {

    private val _card = MutableLiveData<Resource<Card>>()
    val card: LiveData<Resource<Card>>
        get() = _card

    fun fetchCard(cardNumber: String?) {
        viewModelScope.launch {
            try {
                val response = cardRepository.getCard(cardNumber)
               _card.postValue(response)
            }catch (e: Exception) {
                _card.postValue(Resource.error(e.message))
            }
        }
    }
}