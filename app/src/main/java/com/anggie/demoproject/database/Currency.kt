package com.anggie.demoproject.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anggie.demoproject.database.DbUtils.Companion.KEY_NAME
import com.anggie.demoproject.database.DbUtils.Companion.KEY_SYMBOL

@Entity(tableName = "currency_table")
data class Currency (@PrimaryKey(autoGenerate = false) var id: String,
                     @NonNull
                     @ColumnInfo(name= KEY_NAME) var name: String,
                     @NonNull
                     @ColumnInfo(name = KEY_SYMBOL) var symbol: String)