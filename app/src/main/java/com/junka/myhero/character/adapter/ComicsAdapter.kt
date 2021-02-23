package com.junka.myhero.character.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.junka.myhero.R
import com.junka.myhero.character.model.ComicSummaryData
import com.junka.myhero.databinding.LayoutCharacterItemCardBinding
import com.junka.myhero.databinding.LayoutComicItemCardBinding
import kotlin.properties.Delegates

class ComicsAdapter(
    private val listComicSummaryData: List<ComicSummaryData>,
) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    var data: List<ComicSummaryData> by Delegates.observable(listComicSummaryData) { _, _, _ ->
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding : LayoutComicItemCardBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(comicSummary : ComicSummaryData) = with(binding){
            titleTextView.text = comicSummary.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_comic_item_card, parent, false)
        val binding = LayoutComicItemCardBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size
}