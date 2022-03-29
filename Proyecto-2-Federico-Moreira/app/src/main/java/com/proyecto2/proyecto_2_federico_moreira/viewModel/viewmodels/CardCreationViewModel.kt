package com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels

import androidx.lifecycle.*
import com.proyecto2.proyecto_2_federico_moreira.R
import com.proyecto2.proyecto_2_federico_moreira.data.Card
import com.proyecto2.proyecto_2_federico_moreira.data.CardDao
import kotlinx.coroutines.launch

class CardCreationViewModel(val dao: CardDao): ViewModel() {

    private val _canNavigate= MutableLiveData<Boolean>()

    val canNavigate: LiveData<Boolean>
        get() = _canNavigate

    var card = Card()

    fun addCard(){
         viewModelScope.launch{
             selectBackgroundImage(card.imgTemp)
              dao.insertCard(card)
             _canNavigate.value = true
         }
    }

    fun onNavigated(){
        _canNavigate.value = false
    }

    private fun selectBackgroundImage(id:Int){
        when(id){
            R.id.radio_group_value1->  card.image = R.drawable.visainfinite
            R.id.radio_group_value2->  card.image = R.drawable.visaplatinum
            R.id.radio_group_value3->  card.image = R.drawable.visasignature
        }
    }
}