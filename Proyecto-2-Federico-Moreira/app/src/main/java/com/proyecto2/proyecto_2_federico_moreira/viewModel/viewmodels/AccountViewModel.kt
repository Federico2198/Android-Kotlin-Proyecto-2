package com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao

class AccountViewModel(dao: CardDao):ViewModel() {

    // Obtener lista de Cards //
    val cardLiveData = dao.getCards()
    val cardList = dao.getCards()

private val _navigateToCard = MutableLiveData<Long?>()
        val navigateToCard: LiveData<Long?>
            get()=_navigateToCard

    fun onCardClicked(idCard:Long){
        _navigateToCard.value = idCard
    }

    fun onCardNavigated(){
        _navigateToCard.value =null
    }
}