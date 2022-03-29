package com.proyecto2.proyecto_2_federico_moreira.adapters

import androidx.recyclerview.widget.DiffUtil
import com.proyecto2.proyecto_2_federico_moreira.data.Card

class CardDiffItemCallback : DiffUtil.ItemCallback<Card>() {

    override fun areItemsTheSame(oldItem: Card, newItem: Card) = oldItem==newItem

    override fun areContentsTheSame(oldItem: Card, newItem: Card) = oldItem == newItem
}