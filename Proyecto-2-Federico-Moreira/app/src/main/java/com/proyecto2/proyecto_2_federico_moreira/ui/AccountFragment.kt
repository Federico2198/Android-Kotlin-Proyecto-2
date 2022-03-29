package com.proyecto2.proyecto_2_federico_moreira.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.proyecto2.proyecto_2_federico_moreira.adapters.CardItemAdapter
import com.proyecto2.proyecto_2_federico_moreira.data.CardTransferSchema
import com.proyecto2.proyecto_2_federico_moreira.databinding.FragmentAccountBinding
import com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels.AccountViewModel
import com.proyecto2.proyecto_2_federico_moreira.viewModel.factories.AccountViewModelFactory

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = CardTransferSchema.getInstance(application).cardDao
        val viewModelFactory = AccountViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(AccountViewModel::class.java)
        binding.cardViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.createCard.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToCardCreationFragment()
            view.findNavController().navigate(action)
        }

        val adapter = CardItemAdapter { idCard -> viewModel.onCardClicked(idCard) }

        binding.cardList.adapter = adapter
        viewModel.cardList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.navigateToCard.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = AccountFragmentDirections.actionAccountFragmentToCardDetailsFragment(it)
                viewModel.onCardNavigated()
                this.findNavController().navigate(action)
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerForContextMenu(binding.cardList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}