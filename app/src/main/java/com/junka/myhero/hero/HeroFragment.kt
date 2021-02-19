package com.junka.myhero.hero

import androidx.fragment.app.Fragment
import com.junka.myhero.R

class HeroFragment : Fragment(R.layout.fragment_hero) {

    companion object {
        fun newInstance() = HeroFragment().apply {}
    }
}