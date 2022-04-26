package com.anggie.demoproject.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Currency::class], version = 1)
abstract class CurrencyDb : RoomDatabase() {
    abstract fun getCurrencyDao() : CurrencyDao

    private class CurrencyDbCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        private val TAG = "TAG" + CurrencyDbCallback::class.java

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            Log.d(TAG, "onCreateDB")
            INSTANCE?.let { database ->
                scope.launch {
                    val currencyDao = database.getCurrencyDao()

                    var coin = Currency("BTC", "Bitcoin", "BTC" )
                    currencyDao.insert(coin)
                    coin = Currency("ETH", "Ethereum", "ETH")
                    currencyDao.insert(coin)
                    coin = Currency("XRP", "XRP", "XRP")
                    currencyDao.insert(coin)
                    coin = Currency("BCH", "Bitcoin Cash", "BCH")
                    currencyDao.insert(coin)
                    coin = Currency("LTC", "Litecoin", "LTC")
                    currencyDao.insert(coin)
                    coin = Currency("EOS", "EOS", "EOS")
                    currencyDao.insert(coin)
                    coin = Currency("BNB", "Binance Coin", "BNB")
                    currencyDao.insert(coin)
                    coin = Currency("LINK", "Chainlink", "LINK")
                    currencyDao.insert(coin)
                    coin = Currency("NEO", "NEO", "NEO")
                    currencyDao.insert(coin)
                    coin = Currency("ETC", "Ethereum Classic", "ETC")
                    currencyDao.insert(coin)
                    coin = Currency("ONT", "Ontology", "ONT")
                    currencyDao.insert(coin)
                    coin = Currency("CRO", "Crypto.com Chain", "CRO")
                    currencyDao.insert(coin)
                    coin = Currency("CUC", "Cucumber", "CUC")
                    currencyDao.insert(coin)
                    coin = Currency("USDC", "USDC Coin", "USDC")
                    currencyDao.insert(coin)
                }
            }

        }
    }

    companion object {
        private var INSTANCE: CurrencyDb? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CurrencyDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CurrencyDb::class.java,
                    DB_NAME
                ).addCallback(CurrencyDbCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

}