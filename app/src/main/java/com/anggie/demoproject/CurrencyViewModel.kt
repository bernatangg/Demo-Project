package com.anggie.demoproject

import androidx.lifecycle.*
import com.anggie.demoproject.database.Currency
import com.anggie.demoproject.database.CurrencyRepo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CurrencyViewModel(private val currencyRepo: CurrencyRepo): ViewModel() {
    val allCurrency: LiveData<List<Currency>> = currencyRepo.allCurrency.asLiveData()

    fun insert(currency: Currency) = viewModelScope.launch {
        currencyRepo.insert(currency)
    }
}

class CurrencyViewModelFactory(private val repository: CurrencyRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java))
            return CurrencyViewModel(repository) as T

        throw IllegalArgumentException("Unknown View Model")
    }

}