package com.proyecto2.proyecto_2_federico_moreira.viewModel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao
import com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels.AccountViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class AccountViewModelFactory(private val dao: CardDao):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AccountViewModel::class.java)){
            return AccountViewModel(dao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}