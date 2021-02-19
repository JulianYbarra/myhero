package com.junka.myhero.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.junka.myhero.R
import com.junka.myhero.common.getDrawableCompat
import com.junka.myhero.home.adapter.HomeViewPagerAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)

        viewPager.adapter = HomeViewPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.let {
            it.icon = requireContext().getDrawableCompat(R.drawable.ic_superhero)
            it.text = "Personajes"
        }
        tabLayout.getTabAt(1)?.let {
            it.icon = requireContext().getDrawableCompat(R.drawable.ic_calendar)
            it.text = "Calendario"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}