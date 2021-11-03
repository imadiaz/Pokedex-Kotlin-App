package com.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.pokedex.core.BaseViewHolder
import com.pokedex.data.model.Pokemon
import com.pokedex.databinding.PokemonItemRowBinding

class PokemonAdapter(
    private val context: Context,
    private val itemClickListener: OnPokemonClickListener
): ListAdapter<Pokemon, BaseViewHolder<*>>(DiffUtilCallback) {


    interface OnPokemonClickListener {
        fun onPokemonClickListener(pokemon: Pokemon)
    }

    private object DiffUtilCallback: DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PokemonItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is PokemonViewHolder -> holder.bind(getItem(position), position)
            else -> {}
        }
    }

    inner class PokemonViewHolder(private val binding: PokemonItemRowBinding): BaseViewHolder<Pokemon>(binding.root) {
        override fun bind(item: Pokemon, position: Int) = with(binding) {
            Glide.with(context)
                .load(item.image)
                .circleCrop()
                .into(pokemonItemImage)
            pokemonItemName.text = item.name.replaceFirstChar { it.uppercase() }
            pokemonItemCard.setOnClickListener { itemClickListener.onPokemonClickListener(item) }
        }
    }
}