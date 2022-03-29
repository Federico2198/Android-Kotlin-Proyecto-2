package com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao
import com.proyecto2.proyecto_2_federico_moreira.data.TransferDao

class CardInfoViewModel(cardDao: CardDao,
                        idCard: Long, transferDao : TransferDao
                            ): ViewModel() {

    // Obtener Card por ID //
    val card  = cardDao.getCard(idCard)

    val listTransfers = transferDao.getTransfersOfCard(idCard)

    private var _navigateToAddMoney = MutableLiveData<Long?>()
    val navigateToAddMoney: LiveData<Long?>
        get()=_navigateToAddMoney

    private var _navigateToSendMoney = MutableLiveData<Long?>()
    val navigateToSendMoney: LiveData<Long?>
        get()=_navigateToSendMoney

    fun onButtonAddMoneyNavigated(){
        _navigateToAddMoney.value = null
    }

    fun onButtonAddMoneyClicked(idCard:Long){
        _navigateToAddMoney.value = idCard
    }

    fun onButtonSendMoneyNavigated(){
        _navigateToSendMoney.value = null
    }

    fun onButtonSendMoneyClicked(idCard:Long){
        _navigateToSendMoney.value = idCard
    }
}