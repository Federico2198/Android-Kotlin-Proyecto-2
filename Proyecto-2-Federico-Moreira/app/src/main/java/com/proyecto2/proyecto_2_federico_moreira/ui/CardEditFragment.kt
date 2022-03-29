package com.proyecto2.proyecto_2_federico_moreira.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyecto2.proyecto_2_federico_moreira.data.CardTransferSchema
import com.proyecto2.proyecto_2_federico_moreira.databinding.FragmentCardEditBinding
import com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels.CardEditViewModel
import com.proyecto2.proyecto_2_federico_moreira.viewModel.factories.CardEditViewModelFactory

class CardEditFragment : Fragment() {

    private var _binding : FragmentCardEditBinding? = null
        private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardEditBinding.inflate(inflater,container,false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = CardTransferSchema.getInstance(application).cardDao
        val idCard = CardEditFragmentArgs.fromBundle(requireArguments()).idCard
        val delete = CardEditFragmentArgs.fromBundle(requireArguments()).delete

        val viewModelFactory = CardEditViewModelFactory(dao,idCard)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CardEditViewModel::class.java)
        binding.viewModel =  viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToAccount.observe(viewLifecycleOwner, Observer{navigate ->
            if(navigate){
                val action = CardEditFragmentDirections.actionCardEditFragmentToAccountFragment()
                viewModel.onNavigatedToAccount()
                this.findNavController().navigate(action)
            }
        })

        if(delete == "delete"){
            viewModel.delete()
        }

       return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}