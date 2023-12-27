package com.asemlab.samples.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// TODO Add PagerAdapter
class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return BasePageFragment.getInstance("Page #$position")
        /**
         *  TODO Or using WHEN to return different fragments
         */
    }
}