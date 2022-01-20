package com.example.mpaywalletapp.presentation.feature.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpaywalletapp.data.api.StatementService
import com.example.mpaywalletapp.data.repository.StatementRepositoryImpl
import com.example.mpaywalletapp.data.utils.NetworkUtils
import com.example.mpaywalletapp.domain.entity.Statement
import com.example.mpaywalletapp.domain.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class StatementViewModel(private val statementRepository: StatementRepositoryImpl): ViewModel() {
    private val _statementState = MutableLiveData<Resource<Statement>>()
    val statementSate: LiveData<Resource<Statement>>
        get() = _statementState

    fun fetchStatement(statementId: String?) {
        try {
            viewModelScope.launch {
                val response = statementRepository.getStatement(statementId)
                _statementState.postValue(response)
            }
        } catch (e: Exception) {
            _statementState.postValue(Resource.error(e.message))
        }
    }
}