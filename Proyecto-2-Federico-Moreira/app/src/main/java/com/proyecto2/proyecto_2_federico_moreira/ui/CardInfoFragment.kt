package com.proyecto2.proyecto_2_federico_moreira.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyecto2.proyecto_2_federico_moreira.adapters.TransferItemAdapter
import com.proyecto2.proyecto_2_federico_moreira.data.CardTransferSchema
import com.proyecto2.proyecto_2_federico_moreira.databinding.FragmentCardInfoBinding
import com.proyecto2.proyecto_2_federico_moreira.viewModel.factories.CardInfoViewModelFactory
import com.proyecto2.proyecto_2_federico_moreira.viewModel.viewmodels.CardInfoViewModel

class CardInfoFragment : Fragment() {

    private var _binding: FragmentCardInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardInfoBinding.inflate(inflater, container, false)

        val idCard = CardInfoFragmentArgs.fromBundle(requireArguments()).idCard
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val cardDao = CardTransferSchema.getInstance(application).cardDao
        val paymentDao = CardTransferSchema.getInstance(application).transferDao
        val viewModelFactory = CardInfoViewModelFactory(cardDao, idCard, paymentDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CardInfoViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.navigateToAddMoney.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action =
                    CardInfoFragmentDirections.actionCardDetailsFragmentToAddMoneyFragment(it)
                viewModel.onButtonAddMoneyNavigated()
                this.findNavController().navigate(action)
            }
        })

        viewModel.navigateToSendMoney.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action =
                    CardInfoFragmentDirections.actionCardDetailsFragmentToSendMoneyFragment(it)
                viewModel.onButtonSendMoneyNavigated()
                this.findNavController().navigate(action)
            }
        })

        val adapter = TransferItemAdapter()

        binding.transferList.adapter = adapter

        viewModel.listTransfers.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        //    viewModel.card.observe(viewLifecycleOwner, Observer { card->
      //  })

        return view
    }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }