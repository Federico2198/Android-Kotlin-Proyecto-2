package com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao
import com.proyecto2.proyecto_2_federico_moreira.data.Transfer
import com.proyecto2.proyecto_2_federico_moreira.data.TransferDao
import kotlinx.coroutines.launch
import java.util.*

class SendMoneyViewModel(val cardDao: CardDao,
                         val transferDao: TransferDao,
                         val idCard:Long): ViewModel() {

    // Obtener Card por Id //
    val card = cardDao.getCard(idCard)

    val transfer = Transfer()
    var errorMessage: MutableLiveData<String> = MutableLiveData<String>("")

    private val _canNavigate= MutableLiveData<Boolean>()
    val canNavigate: LiveData<Boolean>
        get() = _canNavigate

    // Variable para inicializar money en 0 //
    var money: String = "0"

    fun sendMoney(){
        viewModelScope.launch {
            if(validateMoney()){
                transfer.amountTransfer= money.toDouble()
                transfer.idOfCard= idCard
                transfer.destination="user"
                transferDao.insertTransfer(transfer)
                updateCard()
                _canNavigate.value= true
            }else{
                errorMessage.value = "No cuentas con suficiente dinero"
            }
        }
    }

    private fun updateCard(){
        viewModelScope.launch{
            card.value!!.money -= money.toDouble()
            cardDao.updateCard(card.value!!)
        }
    }

    private fun validateMoney():Boolean{
        return card.value!!.money >= money.toDouble()
    }

    fun onNavigated(){
        _canNavigate.value = false
    }
}