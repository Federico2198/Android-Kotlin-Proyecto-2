package com.proyecto2.proyecto_2_federico_moreira.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyecto2.proyecto_2_federico_moreira.data.CardTransferSchema
import com.proyecto2.proyecto_2_federico_moreira.databinding.FragmentAddMoneyBinding
import com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels.AddMoneyViewModel
import com.proyecto2.proyecto_2_federico_moreira.viewModel.factories.GiveMoneyViewModelFactory

class GiveMoneyFragment : Fragment() {

  private var _binding: FragmentAddMoneyBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMoneyBinding.inflate(inflater, container, false)
       val view = binding.root
        val application = requireNotNull(this.activity).application
        val cardDao = CardTransferSchema.getInstance(application).cardDao
        val transferDao = CardTransferSchema.getInstance(application).transferDao
        val idCard = AddMoneyFragmentArgs.fromBundle(requireArguments()).idCard
        val viewModelFactory = GiveMoneyViewModelFactory(transferDao, cardDao, idCard)
        val viewModel =  ViewModelProvider(this, viewModelFactory).get(AddMoneyViewModel::class.java)
        binding.viewModel= viewModel
        binding.lifecycleOwner= viewLifecycleOwner

        viewModel.canNavigate.observe(viewLifecycleOwner, Observer{navigate->
            if(navigate){
                val action = AddMoneyFragmentDirections.actionAddMoneyFragmentToCardDetailsFragment(idCard)
                viewModel.onNavigated()
                this.findNavController().navigate(action)
            }
        })

        return  view
    }
}