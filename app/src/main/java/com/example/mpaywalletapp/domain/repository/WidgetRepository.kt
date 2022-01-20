package com.example.mpaywalletapp.domain.repository

import com.example.mpaywalletapp.domain.entity.SimpleWidget
import com.example.mpaywalletapp.domain.utils.Resource

interface WidgetRepository {
    suspend fun getWidgets(): Resource<List<SimpleWidget>>
}