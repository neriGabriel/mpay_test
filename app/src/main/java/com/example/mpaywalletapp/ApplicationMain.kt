package com.example.mpaywalletapp

import android.app.Application
import com.example.mpaywalletapp.data.di.cardModule
import com.example.mpaywalletapp.data.di.statementModule
import com.example.mpaywalletapp.data.di.widgetModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationMain: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationMain)
            modules(listOf(widgetModule, cardModule, statementModule))
        }
    }
}