package com.pokedex.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.pokedex.R
import com.pokedex.core.BaseViewHolder
import com.pokedex.data.model.Move
import com.pokedex.data.model.Species
import com.pokedex.databinding.PropertyItemRowBinding
import java.util.*

class PropertyItemAdapter(
    private val context: Context
): ListAdapter<PropertyItem, BaseViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback: DiffUtil.ItemCallback<PropertyItem>() {
        override fun areItemsTheSame(oldItem: PropertyItem, newItem: PropertyItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: PropertyItem, newItem: PropertyItem): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PropertyItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is PropertyViewHolder -> holder.bind(getItem(position), position)
            else -> {}
        }
    }

    inner class PropertyViewHolder(private val binding: PropertyItemRowBinding): BaseViewHolder<PropertyItem>(binding.root) {
        override fun bind(item: PropertyItem, position: Int) {
            if (item.url.isNotEmpty()) {
                Glide.with(context)
                    .load(item.url)
                    .into(binding.img)
            } else {
                Glide.with(context)
                    .load(context.getDrawable(R.drawable.pokeball))
                    .into(binding.img)
            }
            binding.txtName.text = item.name.replaceFirstChar { it.uppercase() }
        }
    }
}



data class PropertyItem(val id: String = UUID.randomUUID().toString().take(10), val name: String, val url: String)
fun Move.toPropertyItem() = PropertyItem(name = move.name, url = "")
fun Species.toPropertyItem(): PropertyItem {
    val urlData = url.split("/")
    val imageUrl = if (urlData.size > 6) {
        urlData[6]
    } else {
        ""
    }
    return PropertyItem(name = name, url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${imageUrl}.png")
}