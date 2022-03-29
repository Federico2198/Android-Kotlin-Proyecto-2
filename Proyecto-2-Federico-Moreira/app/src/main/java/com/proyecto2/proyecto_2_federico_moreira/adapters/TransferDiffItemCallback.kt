package com.proyecto2.proyecto_2_federico_moreira.adapters

import androidx.recyclerview.widget.DiffUtil
import com.proyecto2.proyecto_2_federico_moreira.data.Transfer

class TransferDiffItemCallback: DiffUtil.ItemCallback<Transfer>() {
    override fun areItemsTheSame(oldItem: Transfer, newItem: Transfer) = oldItem==newItem

    override fun areContentsTheSame(oldItem: Transfer, newItem: Transfer) = oldItem == newItem

}