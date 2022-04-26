package com.anggie.demoproject.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CurrencyRepo(private val currencyDao: CurrencyDao) {
    val allCurrency: Flow<List<Currency>> = currencyDao.getCurrencyList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(currency: Currency) {
        currencyDao.insert(currency)
    }
}