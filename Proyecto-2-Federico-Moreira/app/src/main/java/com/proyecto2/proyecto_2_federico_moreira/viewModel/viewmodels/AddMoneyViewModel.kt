package com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao
import com.proyecto2.proyecto_2_federico_moreira.data.Transfer
import com.proyecto2.proyecto_2_federico_moreira.data.TransferDao
import kotlinx.coroutines.launch

class AddMoneyViewModel(val transferDao: TransferDao,
                        val cardDao: CardDao,
                        val idCard:Long): ViewModel() {

    private val _canNavigate= MutableLiveData<Boolean>()

    val canNavigate: LiveData<Boolean>
        get() = _canNavigate

    var transfer = Transfer()

    // Obtener Card por ID //
    val card = cardDao.getCard(idCard)

    var money: String = "0"

    fun addMoney(){
        viewModelScope.launch {
            transfer.amountTransfer=money.toDouble()
            transfer.idOfCard= idCard
            transfer.destination="user"
            transferDao.insertTransfer(transfer)

            updateCard()
            _canNavigate.value= true
        }
    }

    fun updateCard(){
        viewModelScope.launch{
        cardDao.updateCard(card.value!!)
        }
    }

    fun onNavigated(){
        _canNavigate.value = false
    }
}