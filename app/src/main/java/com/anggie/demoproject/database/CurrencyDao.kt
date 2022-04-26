package com.anggie.demoproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency_table")
    fun getCurrencyList(): Flow<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(currency: Currency)
}