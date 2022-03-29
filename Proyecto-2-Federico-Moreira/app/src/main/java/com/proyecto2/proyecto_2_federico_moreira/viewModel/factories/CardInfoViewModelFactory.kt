package com.proyecto2.proyecto_2_federico_moreira.viewModel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao
import com.proyecto2.proyecto_2_federico_moreira.data.TransferDao
import com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels.CardInfoViewModel

@Suppress("UNCHECKED_CAST")
class CardInfoViewModelFactory(private val dao: CardDao,
                               private val idCard: Long,
                               private val transferDao: TransferDao
                                  ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardInfoViewModel::class.java)){
            return CardInfoViewModel(dao, idCard, transferDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}