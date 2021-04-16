package com.what.tomorrow_school_lunch.UI.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.what.tomorrow_school_lunch.UI.main.fragments.TodayMealFragment
import com.what.tomorrow_school_lunch.UI.main.fragments.TodayPhraseFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                TodayPhraseFragment()
            }
            1->{
                TodayMealFragment()
            }
            else->{
                Fragment()
            }

        }
    }

}