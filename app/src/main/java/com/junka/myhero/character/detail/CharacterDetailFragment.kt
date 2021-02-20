package com.junka.myhero.character.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.junka.myhero.R
import com.junka.myhero.character.adapter.ComicsAdapter
import com.junka.myhero.character.model.CharacterData

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    val character: CharacterData? by lazy { arguments?.getParcelable("character") }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        character?.let {
            view.findViewById<Toolbar>(R.id.toolbar).apply {
                title = it.name
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            //TODO buscar solucion de http
            val imagePath = "${it.thumbnail.path}.${it.thumbnail.extension}".replace("http", "https")
            view.findViewById<ImageView>(R.id.imageView).load(imagePath){
                crossfade(true)
            }

            view.findViewById<TextView>(R.id.descriptionTextView).text = it.description

            view.findViewById<RecyclerView>(R.id.comicsRecyclerView).apply {
                adapter = ComicsAdapter(it.comics.items)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterDetailFragment().apply {}
    }
}