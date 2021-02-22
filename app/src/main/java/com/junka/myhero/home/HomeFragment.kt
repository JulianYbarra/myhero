package com.junka.myhero.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.junka.myhero.R
import com.junka.myhero.home.adapter.HomeViewPagerAdapter


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val listText = listOf(R.string.characters, R.string.events)
    private val listIcon = listOf(R.drawable.ic_superhero, R.drawable.ic_calendar)
    private val listIconDisabled = listOf(R.drawable.ic_superhero_disabled,R.drawable.ic_calendar_disabled)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)

        viewPager.adapter = HomeViewPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        for(i in 0..tabLayout.childCount){
            val customTab =  LayoutInflater.from(tabLayout.context).inflate(R.layout.layout_custom_tab, null)

            val firstPosition = i == 0

            customTab.findViewById<TextView>(R.id.tabTextView).apply {
                text = getString(listText[i])
                setTextColor(ContextCompat.getColor(requireContext(),if(firstPosition) R.color.black else R.color.grey_inactive))
            }
            customTab.findViewById<ImageView>(R.id.tabIcon).apply {
                setImageResource(if(firstPosition) listIcon[i] else listIconDisabled[i])
            }

            customTab.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)

            tabLayout.getTabAt(i)?.customView = customTab
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customTab = tab?.customView

                customTab?.let {
                    it.findViewById<TextView>(R.id.tabTextView)
                        .setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    it.findViewById<ImageView>(R.id.tabIcon)
                        .setImageResource(listIcon[tab.position])
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customTab = tab?.customView

                customTab?.let {
                    it.findViewById<TextView>(R.id.tabTextView)
                        .setTextColor(ContextCompat.getColor(requireContext(),R.color.grey_inactive))
                    it.findViewById<ImageView>(R.id.tabIcon)
                        .setImageResource(listIconDisabled[tab.position])
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}