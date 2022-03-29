package com.proyecto2.proyecto_2_federico_moreira.adapters

import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.proyecto2.proyecto_2_federico_moreira.R
import com.proyecto2.proyecto_2_federico_moreira.data.Card
import com.proyecto2.proyecto_2_federico_moreira.databinding.CardItemBinding
import com.proyecto2.proyecto_2_federico_moreira.ui.AccountFragmentDirections

class CardItemAdapter(private val clickListener:(id:Long)->Unit): ListAdapter<Card,
        CardItemAdapter.CardItemViewHolder>(CardDiffItemCallback()) {

    // Crear el onBindViewHolder para Card //
    override fun onBindViewHolder(holder:CardItemViewHolder, position: Int){
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    // Crear el onCreateViewHolder para Card //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder = CardItemViewHolder.inflateFrom(parent)

    class CardItemViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        // Crear el bind para Card //
        fun bind(item: Card, clickListener:(id:Long)->Unit){
            binding.card = item
            binding.imageButton.setImageResource(item.image)
            binding.imageButton.setOnClickListener {clickListener(item.idCard)}
            binding.optionMenuBtn.setOnClickListener(this)
        }

        // Crear el Companion Object para Card //
        companion object{
            fun inflateFrom(parent: ViewGroup):CardItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardItemBinding.inflate(layoutInflater, parent, false)
                return CardItemViewHolder(binding)
            }
        }

        // Crear el Menu de Edit //
        override fun onClick(view: View) {
            val  popUpMenu = PopupMenu(view.context,view)
            popUpMenu.inflate(R.menu.pop_up_menu)
            popUpMenu.setOnMenuItemClickListener(this)
            popUpMenu.show()
        }

        override fun onMenuItemClick(item: MenuItem): Boolean {
            return when(item.itemId){

                R.id.popup_menu_edit -> {
                    val action = AccountFragmentDirections.actionAccountFragmentToCardEditFragment(
                        binding.card!!.idCard
                    )

                    binding.root.findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }
    }
}
