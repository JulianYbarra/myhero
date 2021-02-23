package com.junka.myhero.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junka.myhero.R
import com.junka.myhero.databinding.FragmentEventBinding
import com.junka.myhero.event.adapter.EventAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventFragment : Fragment(R.layout.fragment_event) {

    val viewModel: EventViewModel by viewModel()
    private var binding: FragmentEventBinding? = null

    private val eventAdapter = EventAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.eventList.observe(this) {
            eventAdapter.data = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { binding ->

            val layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView?.let {
                it.adapter = eventAdapter
                it.layoutManager = layoutManager
                it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        viewModel.lastVisible.value = layoutManager.findLastVisibleItemPosition()
                    }
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = EventFragment()
    }
}