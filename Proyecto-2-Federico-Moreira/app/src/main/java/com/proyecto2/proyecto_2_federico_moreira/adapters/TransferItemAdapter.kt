package com.proyecto2.proyecto_2_federico_moreira.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.proyecto2.proyecto_2_federico_moreira.data.Transfer
import com.proyecto2.proyecto_2_federico_moreira.databinding.TransferItemBinding

class TransferItemAdapter : ListAdapter<Transfer, TransferItemAdapter.TransferItemViewHolder>(TransferDiffItemCallback()) {

    override fun onBindViewHolder(holder: TransferItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransferItemViewHolder = TransferItemViewHolder.inflateFrom(parent)

    class TransferItemViewHolder(val binding: TransferItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item : Transfer){
            binding.transfer = item
        }

        companion object{
            fun inflateFrom(parent: ViewGroup):TransferItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TransferItemBinding.inflate(layoutInflater,parent, false)
                return TransferItemViewHolder(binding)
            }
        }
    }
}