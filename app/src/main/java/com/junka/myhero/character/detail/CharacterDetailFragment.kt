package com.junka.myhero.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.junka.myhero.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    val character: CharacterData? by lazy { arguments?.getParcelable("character") }

    private var binding: FragmentCharacterDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        character?.let {
            binding?.let {  binding ->
                binding.toolbar.apply {
                    setNavigationOnClickListener {
                        findNavController().popBackStack()
                    }
                }

                binding.toolbarTitle.apply {
                    text = it.name
                }

                binding.imageView.load(it.thumbnail.getUrl()){
                    crossfade(true)
                }

                binding.descriptionTextView.text = it.description

                binding.comicsRecyclerView.apply {
                    adapter = ComicsAdapter(it.comics.items)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}