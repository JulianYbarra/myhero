package com.junka.myhero.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junka.myhero.R
import com.junka.myhero.character.adapter.CharacterAdapter
import com.junka.myhero.databinding.FragmentHeroBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment(R.layout.fragment_hero) {

    val viewModel: CharacterViewModel by viewModel()
    private var binding: FragmentHeroBinding? = null

    private val characterAdapter = CharacterAdapter{
        val bundle = Bundle().apply {
            putParcelable("character",it)
        }
        findNavController().navigate(R.id.characterDetailFragment,bundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.characterList.observe(this, {
          characterAdapter.submitList(it)
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val layoutManager = LinearLayoutManager(requireContext())

        binding?.let { binding ->
            binding.recyclerView.let {
                it.adapter = characterAdapter
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
        fun newInstance() = CharacterFragment().apply {}
    }
}