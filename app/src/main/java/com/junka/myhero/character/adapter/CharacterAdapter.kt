package com.junka.myhero.character.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.junka.myhero.R
import com.junka.myhero.character.model.CharacterData
import com.junka.myhero.databinding.LayoutCharacterItemCardBinding

class CharacterAdapter(val onClickCharacter : (character: CharacterData) -> Unit) : ListAdapter<CharacterData, CharacterAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_character_item_card, parent, false)
        val binding = LayoutCharacterItemCardBinding.bind(view)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        itemView.setOnClickListener {
            onClickCharacter(item)
        }
    }

    class ItemViewHolder(private val binding: LayoutCharacterItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterData) = with(binding) {
            nameTextView.text = item.name
            descriptionTextView.text = item.description

            val imagePath = "${item.thumbnail.path}.${item.thumbnail.extension}"
            imageView.load(imagePath){
                crossfade(true)
            }

        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<CharacterData>() {
    override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
        return oldItem == newItem
    }
}