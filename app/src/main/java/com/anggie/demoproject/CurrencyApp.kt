package com.anggie.demoproject

import android.app.Application
import com.anggie.demoproject.database.CurrencyDb
import com.anggie.demoproject.database.CurrencyRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CurrencyApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val currencyDb by lazy { CurrencyDb.getDatabase(this, applicationScope) }
    val currencyRepo by lazy { CurrencyRepo(currencyDb.getCurrencyDao()) }
}