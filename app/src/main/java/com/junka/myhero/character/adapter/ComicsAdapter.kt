package com.junka.myhero.character.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.junka.myhero.R
import com.junka.myhero.character.model.ComicSummaryData
import kotlin.properties.Delegates

class ComicsAdapter(
    private val listComicSummaryData: List<ComicSummaryData>,
) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    var data: List<ComicSummaryData> by Delegates.observable(listComicSummaryData) { _, _, _ ->
        notifyDataSetChanged()
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val titleTextView = view.findViewById<TextView>(R.id.titleTextView)

        fun bind(comicSummary : ComicSummaryData){
            titleTextView.text = comicSummary.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_comic_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size
}