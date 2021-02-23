package com.junka.myhero.event.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.junka.myhero.R
import com.junka.myhero.character.adapter.ComicsAdapter
import com.junka.myhero.common.showOrHide
import com.junka.myhero.common.toDateFormat
import com.junka.myhero.databinding.LayoutEventItemCardBinding
import com.junka.myhero.event.model.EventData
import kotlin.properties.Delegates

class EventAdapter(
    private val eventList : List<EventData> = emptyList()
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    var data: List<EventData> by Delegates.observable(eventList) { _, _, _ ->
        notifyDataSetChanged()
    }

    private val expandedList : MutableList<Int> = mutableListOf()

    class ViewHolder(private val binding : LayoutEventItemCardBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(event : EventData) = with(binding){

            val imagePath = "${event.thumbnail.path}.${event.thumbnail.extension}"
            imageView.load(imagePath)

            titleTextView.text = event.title
            startDateTextView.text = event.start.toDateFormat()
            endDateTextView.text = event.end.toDateFormat()

            recyclerView.adapter = ComicsAdapter(event.comics.items)
        }

        fun showOrHideComics(showOrHide : Boolean) = with(binding){
            comicsTextView.showOrHide(showOrHide)
            recyclerView.showOrHide(showOrHide)
            arrowImageView.setImageResource(if(showOrHide) R.drawable.ic_keyboard_arrow_up else R.drawable.ic_keyboard_arrow_down)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_event_item_card, parent, false)
        val binding = LayoutEventItemCardBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.showOrHideComics(expandedList.contains(position))

        holder.itemView.setOnClickListener {
            val expand = toggleLayout(position)
            holder.showOrHideComics(expand)
            notifyItemChanged(position)
        }
    }

    private fun toggleLayout(position: Int) : Boolean{
        return if( expandedList.contains(position)){
            expandedList.remove(position)
            false
        }else{
            expandedList.add(position)
            true
        }
    }

    override fun getItemCount(): Int = data.size
}
