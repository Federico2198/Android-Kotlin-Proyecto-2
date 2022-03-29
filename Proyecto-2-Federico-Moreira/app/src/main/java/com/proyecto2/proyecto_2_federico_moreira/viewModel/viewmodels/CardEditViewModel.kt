package com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto2.proyecto_2_federico_moreira.R
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao
import kotlinx.coroutines.launch

class CardEditViewModel(val dao: CardDao, idCard:Long): ViewModel() {

    // Obtener Card por ID //
    val card = dao.getCard(idCard)

    private val _navigateToAccount= MutableLiveData<Boolean>()
    val navigateToAccount: LiveData<Boolean>
        get() = _navigateToAccount

    fun update(){
        viewModelScope.launch {
            selectBackgroundImage(card.value!!.imgTemp)
            dao.updateCard(card.value!!)
            _navigateToAccount.value = true
        }
    }

    fun delete(){
        viewModelScope.launch {
            dao.deleteCard((card.value!!))
            _navigateToAccount.value = true
        }
    }

    fun onNavigatedToAccount(){
        _navigateToAccount.value = false
    }

    private fun selectBackgroundImage(id:Int){
        when(id){
            R.id.radio_group_value1-> {
                card.value!!.image = R.drawable.visainfinite
            }
            R.id.radio_group_value2-> {
                card.value!!.image = R.drawable.visaplatinum
            }
            R.id.radio_group_value3-> {
                card.value!!.image = R.drawable.visasignature
            }
        }
    }
}