package com.junka.myhero.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.junka.myhero.R
import com.junka.myhero.databinding.FragmentHomeBinding
import com.junka.myhero.databinding.LayoutCustomTabBinding
import com.junka.myhero.home.adapter.HomeViewPagerAdapter


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val listText = listOf(R.string.characters, R.string.events)
    private val listIcon = listOf(R.drawable.ic_superhero, R.drawable.ic_calendar)
    private val listIconDisabled = listOf(R.drawable.ic_superhero_disabled,R.drawable.ic_calendar_disabled)

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { binding ->

            binding.viewPager.adapter = HomeViewPagerAdapter(childFragmentManager)
            binding.tabLayout.setupWithViewPager(binding.viewPager)

            for (i in 0.. binding.tabLayout.childCount) {
                val customTabBinding = LayoutCustomTabBinding.inflate(LayoutInflater.from(requireContext()))

                val firstPosition = i == 0

                customTabBinding.tabTextView.apply {
                    text = getString(listText[i])
                    setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            if (firstPosition) R.color.black else R.color.grey_inactive
                        )
                    )
                }
                customTabBinding.tabIcon.apply {
                    setImageResource(if (firstPosition) listIcon[i] else listIconDisabled[i])
                }

                binding.tabLayout.getTabAt(i)?.customView = customTabBinding.root
            }

            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.customView?.let {
                        val customTabBinding = LayoutCustomTabBinding.bind(it)

                        customTabBinding.tabTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        customTabBinding.tabIcon.setImageResource(listIcon[tab.position])

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView?.let {
                        val customTabBinding = LayoutCustomTabBinding.bind(it)

                        customTabBinding.tabTextView.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grey_inactive
                            )
                        )
                        customTabBinding.tabIcon.setImageResource(listIconDisabled[tab.position])

                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}