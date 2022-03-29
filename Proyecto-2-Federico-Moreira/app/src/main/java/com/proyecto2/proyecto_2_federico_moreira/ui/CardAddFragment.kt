package com.proyecto2.proyecto_2_federico_moreira.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.proyecto2.proyecto_2_federico_moreira.data.CardTransferSchema
import com.proyecto2.proyecto_2_federico_moreira.databinding.FragmentCardAddBinding
import com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels.CardCreationViewModel
import com.proyecto2.proyecto_2_federico_moreira.viewModel.factories.CardCreationViewModelFactory

class CardAddFragment : Fragment() {

    private var _binding: FragmentCardAddBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCardAddBinding.inflate(inflater, container,false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = CardTransferSchema.getInstance(application).cardDao
        val cardCreationViewModelFactory = CardCreationViewModelFactory(dao)
        val viewModel = ViewModelProvider(this,cardCreationViewModelFactory).get(
            CardCreationViewModel::class.java)
        binding.viewModel =  viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.canNavigate.observe(viewLifecycleOwner, Observer { navigate->
            if(navigate){
                val action = CardAddFragmentDirections.actionCardCreationFragmentToAccountFragment()
                viewModel.onNavigated()
                view.findNavController().navigate(action)
            }
        })

        binding.radioGroup.checkedRadioButtonId
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}