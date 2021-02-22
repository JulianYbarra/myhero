package com.junka.myhero.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junka.myhero.R
import com.junka.myhero.RetrofitInstance
import com.junka.myhero.event.adapter.EventAdapter
import com.junka.myhero.event.repository.EventRepository

class EventFragment : Fragment(R.layout.fragment_event) {

    lateinit var viewModel : EventViewModel

    private val eventAdapter = EventAdapter()

    var recyclerView : RecyclerView? = null
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = EventViewModel(EventRepository(RetrofitInstance.eventService))

        viewModel.eventList.observe(this){
            eventAdapter.data = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.let {
            it.adapter = eventAdapter
            it.layoutManager = layoutManager
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    viewModel.lastVisible.value = layoutManager.findLastVisibleItemPosition()
                }
            })
        }
    }

    companion object{
        fun newInstance() = EventFragment()
    }
}