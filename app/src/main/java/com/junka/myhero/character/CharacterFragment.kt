package com.junka.myhero.character

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.junka.myhero.R
import com.junka.myhero.RetrofitInstance
import com.junka.myhero.character.adapter.CharacterAdapter
import com.junka.myhero.character.repository.CharacterRepository

class CharacterFragment : Fragment(R.layout.fragment_hero) {

    lateinit var viewModel: CharacterViewModel

    var recyclerView : RecyclerView? = null

    private val characterAdapter = CharacterAdapter(){
        val bundle = Bundle().apply {
            putParcelable("character",it)
        }
        findNavController().navigate(R.id.characterDetailFragment,bundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = CharacterViewModel(CharacterRepository(RetrofitInstance.characterService))

        viewModel.characterList.observe(this, {
          characterAdapter.submitList(it)
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.let {
            it.adapter = characterAdapter
        }
    }

    companion object {
        fun newInstance() = CharacterFragment().apply {}
    }
}