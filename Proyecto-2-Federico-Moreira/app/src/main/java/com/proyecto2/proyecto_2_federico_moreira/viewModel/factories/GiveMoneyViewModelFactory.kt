package com.proyecto2.proyecto_2_federico_moreira.viewModel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao
import com.proyecto2.proyecto_2_federico_moreira.data.TransferDao
import com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels.AddMoneyViewModel

@Suppress("UNCHECKED_CAST")
class GiveMoneyViewModelFactory(private val transferDao: TransferDao,
                                private val cardDao: CardDao,
                                private val idCard: Long):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMoneyViewModel::class.java)) {
            return AddMoneyViewModel(transferDao, cardDao, idCard) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
