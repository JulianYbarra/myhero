package com.junka.myhero.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.junka.myhero.R
import com.junka.myhero.RetrofitInstance
import com.junka.myhero.event.adapter.EventAdapter
import com.junka.myhero.event.adapter.OnEventClickListener
import com.junka.myhero.event.repository.EventRepository

class EventFragment : Fragment(R.layout.fragment_event) {

    lateinit var viewModel : EventViewModel

    private val onEventClickListener = object : OnEventClickListener{
        override fun onEventClick() {

        }
    }

    private val eventAdapter = EventAdapter(onEventClickListener = onEventClickListener)

    var recyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = EventViewModel(EventRepository(RetrofitInstance.eventService))

        viewModel.eventList.observe(this){
            eventAdapter.data = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.let {
            it.adapter = eventAdapter
        }
    }

    companion object{
        fun newInstance() = EventFragment()
    }
}