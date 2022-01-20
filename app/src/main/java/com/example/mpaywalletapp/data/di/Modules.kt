package com.example.mpaywalletapp.data.di

import com.example.mpaywalletapp.data.api.CardService
import com.example.mpaywalletapp.data.api.StatementService
import com.example.mpaywalletapp.data.api.WidgetService
import com.example.mpaywalletapp.data.repository.CardRepositoryImpl
import com.example.mpaywalletapp.data.repository.StatementRepositoryImpl
import com.example.mpaywalletapp.data.repository.WidgetRepositoryImpl
import com.example.mpaywalletapp.data.utils.NetworkUtils
import com.example.mpaywalletapp.domain.repository.CardRepository
import com.example.mpaywalletapp.domain.repository.StatementRepository
import com.example.mpaywalletapp.domain.repository.WidgetRepository
import com.example.mpaywalletapp.presentation.feature.card.CardViewModel
import com.example.mpaywalletapp.presentation.feature.home.HomeViewModel
import com.example.mpaywalletapp.presentation.feature.statement.StatementViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val widgetModule = module {
    fun provideWidgetService()
        : WidgetService = NetworkUtils.getRetrofitInstance(WidgetService::class.java)
    single { provideWidgetService() }
    factory { WidgetRepositoryImpl(get()) as WidgetRepository}
    factory { WidgetRepositoryImpl(get()) }
    viewModel { HomeViewModel(get()) }
}

val cardModule = module {
    fun provideCardService()
        : CardService = NetworkUtils.getRetrofitInstance(CardService::class.java)
    single { provideCardService() }
    factory { CardRepositoryImpl(get()) as CardRepository }
    factory { CardRepositoryImpl(get()) }
    viewModel { CardViewModel(get()) }
}

val statementModule = module {
    fun provideStatementService()
        : StatementService = NetworkUtils.getRetrofitInstance(StatementService::class.java)
    single { provideStatementService() }
    factory { StatementRepositoryImpl(get()) as StatementRepository }
    factory { StatementRepositoryImpl(get()) }
    viewModel { StatementViewModel(get()) }
}